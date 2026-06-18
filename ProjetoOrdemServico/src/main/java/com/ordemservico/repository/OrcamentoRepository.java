package com.ordemservico.repository;

import com.ordemservico.model.Orcamento;
import com.ordemservico.model.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrcamentoRepository extends JpaRepository<Orcamento, Long> {

    List<Orcamento> findByStatus(StatusSolicitacao status);

    List<Orcamento> findByOrderByDataEnvioDesc();
}
