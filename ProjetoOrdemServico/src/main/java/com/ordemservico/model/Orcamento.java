package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orcamentos")
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tipoServico;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusSolicitacao status;

    public Orcamento() {
        this.dataEnvio = LocalDateTime.now();
        this.status = StatusSolicitacao.PENDENTE;
    }

    public Orcamento(String nome, String telefone, String email,
                     String tipoServico, String descricao) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.tipoServico = tipoServico;
        this.descricao = descricao;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
