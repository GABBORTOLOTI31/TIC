package com.ordemservico.model;

import jakarta.persistence.*;

@Entity
@Table(name = "equipamentos")
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false, unique = true)
    private String numeroSerie;

    @Column(nullable = false)
    private String problemaRelatado;

    public Equipamento() {
    }

    public Equipamento(String modelo, String marca, String numeroSerie, String problemaRelatado) {
        this.modelo = modelo;
        this.marca = marca;
        this.numeroSerie = numeroSerie;
        this.problemaRelatado = problemaRelatado;
    }

    public Long getId() {
        return id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getProblemaRelatado() {
        return problemaRelatado;
    }

    public void setProblemaRelatado(String problemaRelatado) {
        this.problemaRelatado = problemaRelatado;
    }
}
