package com.ordemservico.service;

import com.ordemservico.model.Servico;
import com.ordemservico.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    public List<Servico> listarTodos() {
        return servicoRepository.findAll();
    }

    public List<Servico> listarAtivos() {
        return servicoRepository.findByAtivo(true);
    }

    public Servico buscarPorId(Long id) {
        return servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado. ID: " + id));
    }

    public List<Servico> buscarPorNome(String nome) {
        return servicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Servico salvar(Servico servico) {
        return servicoRepository.save(servico);
    }

    public Servico atualizar(Long id, Servico servicoAtualizado) {
        Servico servicoExistente = buscarPorId(id);

        servicoExistente.setNome(servicoAtualizado.getNome());
        servicoExistente.setDescricao(servicoAtualizado.getDescricao());
        servicoExistente.setValorBase(servicoAtualizado.getValorBase());
        servicoExistente.setDuracaoEstimada(servicoAtualizado.getDuracaoEstimada());

        return servicoRepository.save(servicoExistente);
    }

    public Servico ativarDesativar(Long id) {
        Servico servico = buscarPorId(id);
        servico.setAtivo(!servico.isAtivo());
        return servicoRepository.save(servico);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        servicoRepository.deleteById(id);
    }
}
