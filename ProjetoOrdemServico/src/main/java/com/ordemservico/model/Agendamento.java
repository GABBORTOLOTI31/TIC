package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "agendamentos")
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "servico_id", nullable = false)
    private Servico servico;

    @Column(nullable = false)
    private LocalDateTime dataHoraMarcada;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAgendamento status;

    public Agendamento() {
        this.status = StatusAgendamento.PENDENTE;
    }

    public Agendamento(Cliente cliente, Tecnico tecnico, Servico servico,
                       LocalDateTime dataHoraMarcada, String observacoes) {
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.servico = servico;
        this.dataHoraMarcada = dataHoraMarcada;
        this.observacoes = observacoes;
        this.status = StatusAgendamento.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public LocalDateTime getDataHoraMarcada() {
        return dataHoraMarcada;
    }

    public void setDataHoraMarcada(LocalDateTime dataHoraMarcada) {
        this.dataHoraMarcada = dataHoraMarcada;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public StatusAgendamento getStatus() {
        return status;
    }

    public void setStatus(StatusAgendamento status) {
        this.status = status;
    }
}
