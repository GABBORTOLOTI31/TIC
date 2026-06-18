package com.ordemservico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "materiais")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false, length = 10)
    private String unidade;

    @Column(nullable = false)
    private Double precoUnitario;

    public Material() {
    }

    public Material(String descricao, String unidade, Double precoUnitario) {
        this.descricao = descricao;
        this.unidade = unidade;
        this.precoUnitario = precoUnitario;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(Double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
}
