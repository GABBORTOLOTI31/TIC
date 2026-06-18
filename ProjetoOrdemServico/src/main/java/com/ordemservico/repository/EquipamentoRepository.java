package com.ordemservico.repository;

import com.ordemservico.model.Equipamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long> {

    Optional<Equipamento> findByNumeroSerie(String numeroSerie);

    boolean existsByNumeroSerie(String numeroSerie);
}
