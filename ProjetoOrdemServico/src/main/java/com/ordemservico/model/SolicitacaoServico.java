package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "solicitacoes_servico")
public class SolicitacaoServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String tipoServico;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolicitacao status;

    public SolicitacaoServico() {
        this.dataEnvio = LocalDateTime.now();
        this.status = StatusSolicitacao.PENDENTE;
    }

    public SolicitacaoServico(String nome, String telefone, String endereco,
                               String tipoServico, String observacoes) {
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipoServico = tipoServico;
        this.observacoes = observacoes;
        this.dataEnvio = LocalDateTime.now();
        this.status = StatusSolicitacao.PENDENTE;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public StatusSolicitacao getStatus() {
        return status;
    }

    public void setStatus(StatusSolicitacao status) {
        this.status = status;
    }
}
