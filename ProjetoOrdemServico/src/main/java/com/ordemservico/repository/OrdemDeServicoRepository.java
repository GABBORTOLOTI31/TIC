package com.ordemservico.repository;

import com.ordemservico.model.OrdemDeServico;
import com.ordemservico.model.StatusOS;
import com.ordemservico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico, Long> {

    List<OrdemDeServico> findByStatus(StatusOS status);

    List<OrdemDeServico> findByTecnico(Tecnico tecnico);
}
