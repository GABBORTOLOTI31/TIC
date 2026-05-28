
package com.ordemservico;

public class Equipamento {
    private int id;
    private String modelo;
    private String marca;
    private String numeroSerie;
    private String problemaRelacionado;

    public Equipamento(int id, String modelo, String marca,
                        String numeroSerie, String problemaRelacionado) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.problemaRelacionado = problemaRelacionado;
    }

    public void exibirDados() {
        System.out.println("Equipamento: " + marca + " " + modelo);
        System.out.println("Número Série: " + numeroSerie);
        System.out.println("Problema: " + problemaRelacionado);
    }
}
