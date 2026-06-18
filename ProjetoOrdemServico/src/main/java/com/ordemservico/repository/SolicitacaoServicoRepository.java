package com.ordemservico.repository;

import com.ordemservico.model.SolicitacaoServico;
import com.ordemservico.model.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitacaoServicoRepository extends JpaRepository<SolicitacaoServico, Long> {

    List<SolicitacaoServico> findByStatus(StatusSolicitacao status);

    List<SolicitacaoServico> findByOrderByDataEnvioDesc();
}
