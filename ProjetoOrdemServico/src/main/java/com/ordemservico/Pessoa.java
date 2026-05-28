
package com.ordemservico;

public abstract class Pessoa {
    protected int id;
    protected String nome;
    protected String cpf;
    protected String telefone;
    protected String email;

    public Pessoa(int id, String nome, String cpf, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public abstract void exibirDados();
}
