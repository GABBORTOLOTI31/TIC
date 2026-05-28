
package com.ordemservico;

public class Material {
    private int id;
    private String descricao;
    private String unidade;
    private double precoUnitario;

    public Material(int id, String descricao, String unidade, double precoUnitario) {
        this.id = id;
        this.descricao = descricao;
        this.unidade = unidade;
        this.precoUnitario = precoUnitario;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void exibirDados() {
        System.out.println("Material: " + descricao);
        System.out.println("Unidade: " + unidade);
        System.out.println("Preço: R$ " + precoUnitario);
    }
}
