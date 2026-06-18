package com.ordemservico.service;

import com.ordemservico.model.Agendamento;
import com.ordemservico.model.Servico;
import com.ordemservico.model.StatusAgendamento;
import com.ordemservico.repository.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ClienteService clienteService;

    public Agendamento salvar(Agendamento agendamento) {
        // Valida se as entidades relacionadas existem
        clienteService.buscarPorId(agendamento.getCliente().getId());
        tecnicoService.buscarPorId(agendamento.getTecnico().getId());
        Servico servico = servicoService.buscarPorId(agendamento.getServico().getId());

        agendamento.setServico(servico); // Garante que o servico buscado (com duracaoEstimada) seja usado na validacao

        validarConflitoDeHorario(agendamento);

        return agendamentoRepository.save(agendamento);
    }

    public Agendamento atualizar(Long id, Agendamento agendamentoAtualizado) {
        Agendamento existente = buscarPorId(id);

        clienteService.buscarPorId(agendamentoAtualizado.getCliente().getId());
        tecnicoService.buscarPorId(agendamentoAtualizado.getTecnico().getId());
        Servico servico = servicoService.buscarPorId(agendamentoAtualizado.getServico().getId());

        agendamentoAtualizado.setServico(servico);

        // Se mudou o tecnico ou a data/hora, valida conflito novamente
        boolean dataMudou = !existente.getDataHoraMarcada().equals(agendamentoAtualizado.getDataHoraMarcada());
        boolean tecnicoMudou = !existente.getTecnico().getId().equals(agendamentoAtualizado.getTecnico().getId());

        if (dataMudou || tecnicoMudou) {
            validarConflitoDeHorario(agendamentoAtualizado);
        }

        existente.setCliente(agendamentoAtualizado.getCliente());
        existente.setTecnico(agendamentoAtualizado.getTecnico());
        existente.setServico(agendamentoAtualizado.getServico());
        existente.setDataHoraMarcada(agendamentoAtualizado.getDataHoraMarcada());
        existente.setObservacoes(agendamentoAtualizado.getObservacoes());

        return agendamentoRepository.save(existente);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Agendamento buscarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado. ID: " + id));
    }

    public Agendamento atualizarStatus(Long id, StatusAgendamento novoStatus) {
        Agendamento agendamento = buscarPorId(id);
        agendamento.setStatus(novoStatus);
        return agendamentoRepository.save(agendamento);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        agendamentoRepository.deleteById(id);
    }

    private void validarConflitoDeHorario(Agendamento agendamento) {
        LocalDateTime inicio = agendamento.getDataHoraMarcada();
        Integer duracaoEstimada = agendamento.getServico().getDuracaoEstimada();
        
        if(duracaoEstimada == null) {
            duracaoEstimada = 60; // fallback de 1 hora
        }
        
        LocalDateTime fim = inicio.plusMinutes(duracaoEstimada);

        // Busca agendamentos do tecnico que começam no mesmo dia e podem conflitar.
        // Pega margem ampla para simplificar: o dia todo do inicio.
        LocalDateTime inicioDia = inicio.toLocalDate().atStartOfDay();
        LocalDateTime fimDia = inicioDia.plusDays(1).minusNanos(1);

        List<Agendamento> agendamentosDoDia = agendamentoRepository
                .findByTecnicoAndDataHoraMarcadaBetween(agendamento.getTecnico(), inicioDia, fimDia);

        for (Agendamento existente : agendamentosDoDia) {
            if (existente.getId() != null && existente.getId().equals(agendamento.getId())) {
                continue; // Nao conflita com ele mesmo na atualizacao
            }

            if (existente.getStatus() == StatusAgendamento.CANCELADO || existente.getStatus() == StatusAgendamento.CONCLUIDO) {
                continue; // Agendamentos cancelados ou concluidos nao geram conflito (assumindo que concluido ja passou)
            }

            Integer duracaoExistente = existente.getServico().getDuracaoEstimada();
            if(duracaoExistente == null) duracaoExistente = 60;
            
            LocalDateTime inicioExistente = existente.getDataHoraMarcada();
            LocalDateTime fimExistente = inicioExistente.plusMinutes(duracaoExistente);

            // Verifica sobreposicao de intervalos (inicio1 < fim2 && fim1 > inicio2)
            if (inicio.isBefore(fimExistente) && fim.isAfter(inicioExistente)) {
                throw new RuntimeException("Conflito de horário! O técnico já possui um agendamento das " +
                        inicioExistente.toLocalTime() + " às " + fimExistente.toLocalTime() + ".");
            }
        }
    }
}
