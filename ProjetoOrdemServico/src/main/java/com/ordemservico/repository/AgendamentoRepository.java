package com.ordemservico.repository;

import com.ordemservico.model.Agendamento;
import com.ordemservico.model.StatusAgendamento;
import com.ordemservico.model.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {

    List<Agendamento> findByTecnico(Tecnico tecnico);

    List<Agendamento> findByStatus(StatusAgendamento status);

    List<Agendamento> findByDataHoraMarcadaBetween(LocalDateTime inicio, LocalDateTime fim);

    List<Agendamento> findByTecnicoAndDataHoraMarcadaBetween(Tecnico tecnico, LocalDateTime inicio, LocalDateTime fim);
}
