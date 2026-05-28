package com.ordemservico;

import java.time.LocalDate;

public class Cliente extends Pessoa {
    private LocalDate dataCadastro;
    private String endereco;

    public Cliente(int id, String nome, String cpf, String telefone, String email,
                   LocalDate dataCadastro, String endereco) {
        super(id, nome, cpf, telefone, email);
        this.dataCadastro = dataCadastro;
        this.endereco = endereco;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public void exibirDados() {
        System.out.println("=== CLIENTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
        System.out.println("Telefone: " + telefone);
        System.out.println("Email: " + email);
        System.out.println("Endereço: " + endereco);
        System.out.println("Data Cadastro: " + dataCadastro);
    }
}