package com.ordemservico.repository;

import com.ordemservico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {

    List<Tecnico> findByNomeContainingIgnoreCase(String nome);

    List<Tecnico> findByAtivo(boolean ativo);

    boolean existsByNrRegistro(String nrRegistro);

    boolean existsByCpf(String cpf);
}
