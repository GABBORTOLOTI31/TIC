
package com.ordemservico;

public class Tecnico extends Pessoa {
    private String especialidade;
    private String nrRegistro;

    public Tecnico(int id, String nome, String cpf, String telefone, String email,
                    String especialidade, String nrRegistro) {
        super(id, nome, cpf, telefone, email);
        this.especialidade = especialidade;
        this.nrRegistro = nrRegistro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public String getNrRegistro() {
        return nrRegistro;
    }

    @Override
    public void exibirDados() {
        System.out.println("=== TÉCNICO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Especialidade: " + especialidade);
        System.out.println("Registro: " + nrRegistro);
    }
}
