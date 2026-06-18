package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "clientes")
public class Cliente extends Pessoa {

    @Column(nullable = false)
    private LocalDate dataCadastro;

    @Column(nullable = false)
    private String endereco;

    public Cliente() {
    }

    public Cliente(String nome, String cpf, String telefone, String email,
                   LocalDate dataCadastro, String endereco) {
        super(nome, cpf, telefone, email);
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
