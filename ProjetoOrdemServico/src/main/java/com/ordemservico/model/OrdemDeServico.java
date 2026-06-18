package com.ordemservico.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens_servico")
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(optional = false)
    @JoinColumn(name = "tecnico_id", nullable = false)
    private Tecnico tecnico;

    @ManyToOne(optional = false)
    @JoinColumn(name = "equipamento_id", nullable = false)
    private Equipamento equipamento;

    @OneToOne
    @JoinColumn(name = "agendamento_id")
    private Agendamento agendamento;

    @Column(nullable = false)
    private LocalDateTime dataAbertura;

    private LocalDateTime dataConclusao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusOS status;

    @Column(columnDefinition = "TEXT")
    private String diagnosticoTecnico;

    @Column(nullable = false)
    private Double valorMaoDeObra;

    @Column(nullable = false)
    private Double valorTotal;

    @OneToMany(mappedBy = "ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemMaterial> itensMaterial = new ArrayList<>();

    @OneToOne(mappedBy = "ordemDeServico", cascade = CascadeType.ALL, orphanRemoval = true)
    private Checklist checklist;

    public OrdemDeServico() {
        this.status = StatusOS.ABERTA;
        this.dataAbertura = LocalDateTime.now();
        this.valorMaoDeObra = 0.0;
        this.valorTotal = 0.0;
    }

    public OrdemDeServico(Cliente cliente, Tecnico tecnico, Equipamento equipamento,
                          Agendamento agendamento, Double valorMaoDeObra) {
        this.cliente = cliente;
        this.tecnico = tecnico;
        this.equipamento = equipamento;
        this.agendamento = agendamento;
        this.valorMaoDeObra = valorMaoDeObra;
        this.status = StatusOS.ABERTA;
        this.dataAbertura = LocalDateTime.now();
        this.valorTotal = valorMaoDeObra; // Será recalculado ao adicionar materiais
    }

    public Long getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }

    public Agendamento getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(Agendamento agendamento) {
        this.agendamento = agendamento;
    }

    public LocalDateTime getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDateTime dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(LocalDateTime dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public StatusOS getStatus() {
        return status;
    }

    public void setStatus(StatusOS status) {
        this.status = status;
    }

    public String getDiagnosticoTecnico() {
        return diagnosticoTecnico;
    }

    public void setDiagnosticoTecnico(String diagnosticoTecnico) {
        this.diagnosticoTecnico = diagnosticoTecnico;
    }

    public Double getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(Double valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
        recalcularValorTotal();
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void recalcularValorTotal() {
        double totalMateriais = 0.0;
        if (itensMaterial != null) {
            for (ItemMaterial item : itensMaterial) {
                totalMateriais += item.getValorTotalItem();
            }
        }
        this.valorTotal = this.valorMaoDeObra + totalMateriais;
    }

    public List<ItemMaterial> getItensMaterial() {
        return itensMaterial;
    }

    public void adicionarMaterial(ItemMaterial item) {
        itensMaterial.add(item);
        item.setOrdemDeServico(this);
        recalcularValorTotal();
    }

    public void removerMaterial(ItemMaterial item) {
        itensMaterial.remove(item);
        item.setOrdemDeServico(null);
        recalcularValorTotal();
    }

    public Checklist getChecklist() {
        return checklist;
    }

    public void setChecklist(Checklist checklist) {
        this.checklist = checklist;
        if(checklist != null) {
            checklist.setOrdemDeServico(this);
        }
    }
}
