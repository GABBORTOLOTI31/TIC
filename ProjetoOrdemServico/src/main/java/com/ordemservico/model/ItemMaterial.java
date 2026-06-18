package com.ordemservico.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "os_materiais")
public class ItemMaterial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "ordem_servico_id", nullable = false)
    @JsonIgnore
    private OrdemDeServico ordemDeServico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    @Column(nullable = false)
    private Double quantidade;

    @Column(nullable = false)
    private Double precoUnitarioCobrado;

    public ItemMaterial() {
    }

    public ItemMaterial(Material material, Double quantidade) {
        this.material = material;
        this.quantidade = quantidade;
        this.precoUnitarioCobrado = material.getPrecoUnitario();
    }

    public Long getId() {
        return id;
    }

    public OrdemDeServico getOrdemDeServico() {
        return ordemDeServico;
    }

    public void setOrdemDeServico(OrdemDeServico ordemDeServico) {
        this.ordemDeServico = ordemDeServico;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
        if(this.precoUnitarioCobrado == null && material != null) {
            this.precoUnitarioCobrado = material.getPrecoUnitario();
        }
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPrecoUnitarioCobrado() {
        return precoUnitarioCobrado;
    }

    public void setPrecoUnitarioCobrado(Double precoUnitarioCobrado) {
        this.precoUnitarioCobrado = precoUnitarioCobrado;
    }

    public Double getValorTotalItem() {
        if(quantidade != null && precoUnitarioCobrado != null) {
            return quantidade * precoUnitarioCobrado;
        }
        return 0.0;
    }
}
