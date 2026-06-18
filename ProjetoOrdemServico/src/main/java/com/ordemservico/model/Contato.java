package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contatos")
public class Contato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, length = 20)
    private String telefone;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @Column(nullable = false)
    private LocalDateTime dataEnvio;

    public Contato() {
        this.dataEnvio = LocalDateTime.now();
    }

    public Contato(String nome, String telefone, String mensagem) {
        this.nome = nome;
        this.telefone = telefone;
        this.mensagem = mensagem;
        this.dataEnvio = LocalDateTime.now();
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

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }
}
