package com.ordemservico.service;

import com.ordemservico.model.SolicitacaoServico;
import com.ordemservico.model.StatusSolicitacao;
import com.ordemservico.repository.SolicitacaoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitacaoServicoService {

    @Autowired
    private SolicitacaoServicoRepository solicitacaoServicoRepository;

    public SolicitacaoServico salvar(SolicitacaoServico solicitacao) {
        return solicitacaoServicoRepository.save(solicitacao);
    }

    public List<SolicitacaoServico> listarTodas() {
        return solicitacaoServicoRepository.findByOrderByDataEnvioDesc();
    }

    public List<SolicitacaoServico> listarPorStatus(StatusSolicitacao status) {
        return solicitacaoServicoRepository.findByStatus(status);
    }

    public SolicitacaoServico buscarPorId(Long id) {
        return solicitacaoServicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitação de serviço não encontrada. ID: " + id));
    }

    public SolicitacaoServico atualizarStatus(Long id, StatusSolicitacao novoStatus) {
        SolicitacaoServico solicitacao = buscarPorId(id);
        solicitacao.setStatus(novoStatus);
        return solicitacaoServicoRepository.save(solicitacao);
    }
}
