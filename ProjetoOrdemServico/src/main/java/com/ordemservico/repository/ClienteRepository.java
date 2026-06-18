package com.ordemservico.repository;

import com.ordemservico.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    Optional<Cliente> findByCpf(String cpf);

    boolean existsByCpf(String cpf);

    boolean existsByEmail(String email);
}
