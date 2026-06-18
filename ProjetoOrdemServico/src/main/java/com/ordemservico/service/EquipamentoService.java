package com.ordemservico.service;

import com.ordemservico.model.Equipamento;
import com.ordemservico.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    public List<Equipamento> listarTodos() {
        return equipamentoRepository.findAll();
    }

    public Equipamento buscarPorId(Long id) {
        return equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado. ID: " + id));
    }

    public Equipamento salvar(Equipamento equipamento) {
        if (equipamentoRepository.existsByNumeroSerie(equipamento.getNumeroSerie())) {
            throw new RuntimeException("Já existe um equipamento com o número de série: " + equipamento.getNumeroSerie());
        }
        return equipamentoRepository.save(equipamento);
    }

    public Equipamento atualizar(Long id, Equipamento equipamentoAtualizado) {
        Equipamento equipamentoExistente = buscarPorId(id);

        equipamentoExistente.setModelo(equipamentoAtualizado.getModelo());
        equipamentoExistente.setMarca(equipamentoAtualizado.getMarca());
        equipamentoExistente.setNumeroSerie(equipamentoAtualizado.getNumeroSerie());
        equipamentoExistente.setProblemaRelatado(equipamentoAtualizado.getProblemaRelatado());

        return equipamentoRepository.save(equipamentoExistente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        equipamentoRepository.deleteById(id);
    }
}
