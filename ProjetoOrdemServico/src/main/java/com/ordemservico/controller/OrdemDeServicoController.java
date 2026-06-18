package com.ordemservico.controller;

import com.ordemservico.model.Checklist;
import com.ordemservico.model.OrdemDeServico;
import com.ordemservico.model.StatusOS;
import com.ordemservico.service.OrdemDeServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-servico")
public class OrdemDeServicoController {

    @Autowired
    private OrdemDeServicoService ordemDeServicoService;

    @GetMapping
    public ResponseEntity<List<OrdemDeServico>> listarTodas() {
        return ResponseEntity.ok(ordemDeServicoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdemDeServico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ordemDeServicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<OrdemDeServico> criar(@RequestBody OrdemDeServico os) {
        OrdemDeServico salva = ordemDeServicoService.salvar(os);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdemDeServico> atualizarStatus(@PathVariable Long id, @RequestParam StatusOS status) {
        return ResponseEntity.ok(ordemDeServicoService.atualizarStatus(id, status));
    }

    @PostMapping("/{id}/materiais")
    public ResponseEntity<OrdemDeServico> adicionarMaterial(
            @PathVariable Long id,
            @RequestParam Long idMaterial,
            @RequestParam Double quantidade) {
        return ResponseEntity.ok(ordemDeServicoService.adicionarMaterial(id, idMaterial, quantidade));
    }

    @PostMapping("/{id}/checklist")
    public ResponseEntity<OrdemDeServico> definirChecklist(
            @PathVariable Long id,
            @RequestBody Checklist checklist) {
        return ResponseEntity.ok(ordemDeServicoService.definirChecklist(id, checklist));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ordemDeServicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
