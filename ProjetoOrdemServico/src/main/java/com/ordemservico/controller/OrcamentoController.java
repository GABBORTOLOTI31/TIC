package com.ordemservico.controller;

import com.ordemservico.model.Orcamento;
import com.ordemservico.model.StatusSolicitacao;
import com.ordemservico.service.OrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orcamentos")
public class OrcamentoController {

    @Autowired
    private OrcamentoService orcamentoService;

    @GetMapping
    public ResponseEntity<List<Orcamento>> listarTodos() {
        return ResponseEntity.ok(orcamentoService.listarTodos());
    }

    @GetMapping("/status")
    public ResponseEntity<List<Orcamento>> listarPorStatus(@RequestParam StatusSolicitacao status) {
        return ResponseEntity.ok(orcamentoService.listarPorStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orcamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(orcamentoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Orcamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusSolicitacao status) {
        return ResponseEntity.ok(orcamentoService.atualizarStatus(id, status));
    }
}
