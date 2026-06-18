package com.ordemservico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "checklists")
public class Checklist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    @JsonIgnore
    private OrdemDeServico ordemDeServico;

    @Column(nullable = false)
    private boolean pressaoOk;

    @Column(nullable = false)
    private boolean temperaturaOk;

    @Column(nullable = false)
    private boolean limpezaOk;

    @Column(nullable = false)
    private boolean vazamentoVerificado;

    @Column(columnDefinition = "TEXT")
    private String observacoesGerais;

    public Checklist() {
    }

    public Checklist(boolean pressaoOk, boolean temperaturaOk, boolean limpezaOk, boolean vazamentoVerificado, String observacoesGerais) {
        this.pressaoOk = pressaoOk;
        this.temperaturaOk = temperaturaOk;
        this.limpezaOk = limpezaOk;
        this.vazamentoVerificado = vazamentoVerificado;
        this.observacoesGerais = observacoesGerais;
    }

    public Long getId() {
        return id;
    }

    public OrdemDeServico getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public boolean isPressaoOk() {
        return pressaoOk;
    }

    public void setPressaoOk(boolean pressaoOk) {
        this.pressaoOk = pressaoOk;
    }

    public boolean isTemperaturaOk() {
        return temperaturaOk;
    }

    public void setTemperaturaOk(boolean temperaturaOk) {
        this.temperaturaOk = temperaturaOk;
    }

    public boolean isLimpezaOk() {
        return limpezaOk;
    }

    public void setLimpezaOk(boolean limpezaOk) {
        this.limpezaOk = limpezaOk;
    }

    public boolean isVazamentoVerificado() {
        return vazamentoVerificado;
    }

    public void setVazamentoVerificado(boolean vazamentoVerificado) {
        this.vazamentoVerificado = vazamentoVerificado;
    }

    public String getObservacoesGerais() {
        return observacoesGerais;
    }

    public void setObservacoesGerais(String observacoesGerais) {
        this.observacoesGerais = observacoesGerais;
    }
}
