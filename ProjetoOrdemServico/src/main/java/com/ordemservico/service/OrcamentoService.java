package com.ordemservico.service;

import com.ordemservico.model.Orcamento;
import com.ordemservico.model.StatusSolicitacao;
import com.ordemservico.repository.OrcamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrcamentoService {

    @Autowired
    private OrcamentoRepository orcamentoRepository;

    public Orcamento salvar(Orcamento orcamento) {
        return orcamentoRepository.save(orcamento);
    }

    public List<Orcamento> listarTodos() {
        return orcamentoRepository.findByOrderByDataEnvioDesc();
    }

    public List<Orcamento> listarPorStatus(StatusSolicitacao status) {
        return orcamentoRepository.findByStatus(status);
    }

    public Orcamento buscarPorId(Long id) {
        return orcamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Orçamento não encontrado. ID: " + id));
    }

    public Orcamento atualizarStatus(Long id, StatusSolicitacao novoStatus) {
        Orcamento orcamento = buscarPorId(id);
        orcamento.setStatus(novoStatus);
        return orcamentoRepository.save(orcamento);
    }
}
