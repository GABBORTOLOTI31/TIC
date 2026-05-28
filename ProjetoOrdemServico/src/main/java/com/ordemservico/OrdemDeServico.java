
package com.ordemservico;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdemDeServico implements ICalculavel {

    private int numero;
    private LocalDate dataAbertura;
    private String descricao;
    private String status;
    private double valorServico;

    private Cliente cliente;
    private Tecnico tecnico;

    private List<Equipamento> equipamentos;
    private List<Material> materiais;

    public OrdemDeServico(int numero, LocalDate dataAbertura, String descricao,
                        String status, double valorServico,
                        Cliente cliente, Tecnico tecnico) {

        this.numero = numero;
        this.dataAbertura = dataAbertura;
        this.descricao = descricao;
        this.status = status;
        this.valorServico = valorServico;
        this.cliente = cliente;
        this.tecnico = tecnico;

        this.equipamentos = new ArrayList<>();
        this.materiais = new ArrayList<>();
    }

    public void adicionarEquipamento(Equipamento equipamento) {
        equipamentos.add(equipamento);
    }

    public void removerEquipamento(Equipamento equipamento) {
        equipamentos.remove(equipamento);
    }

    public void adicionarMaterial(Material material) {
        materiais.add(material);
    }

    @Override
    public double calcularTotal() {
        double totalMateriais = 0;

        for (Material material : materiais) {
            totalMateriais += material.getPrecoUnitario();
        }

        return valorServico + totalMateriais;
    }

    public void exibirDados() {
        System.out.println("\n===== ORDEM DE SERVIÇO =====");
        System.out.println("Número: " + numero);
        System.out.println("Data: " + dataAbertura);
        System.out.println("Descrição: " + descricao);
        System.out.println("Status: " + status);

        System.out.println("\nCliente:");
        cliente.exibirDados();

        System.out.println("\nTécnico:");
        tecnico.exibirDados();

        System.out.println("\nEquipamentos:");
        for (Equipamento equipamento : equipamentos) {
            equipamento.exibirDados();
        }

        System.out.println("\nMateriais:");
        for (Material material : materiais) {
            material.exibirDados();
        }

        System.out.println("\nValor Serviço: R$ " + valorServico);
        System.out.println("TOTAL: R$ " + calcularTotal());
    }
}
