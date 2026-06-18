package com.ordemservico.repository;

import com.ordemservico.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    List<Servico> findByAtivo(boolean ativo);

    List<Servico> findByNomeContainingIgnoreCase(String nome);
}
