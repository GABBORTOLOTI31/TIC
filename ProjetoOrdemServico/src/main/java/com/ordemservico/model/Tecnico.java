package com.ordemservico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tecnicos")
public class Tecnico extends Pessoa {

    @Column(nullable = false)
    private String especialidade;

    @Column(nullable = false, unique = true, length = 20)
    private String nrRegistro;

    @Column(nullable = false)
    private boolean ativo;

    public Tecnico() {
    }

    public Tecnico(String nome, String cpf, String telefone, String email,
                   String especialidade, String nrRegistro) {
        super(nome, cpf, telefone, email);
        this.especialidade = especialidade;
        this.nrRegistro = nrRegistro;
        this.ativo = true;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getNrRegistro() {
        return nrRegistro;
    }

    public void setNrRegistro(String nrRegistro) {
        this.nrRegistro = nrRegistro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
