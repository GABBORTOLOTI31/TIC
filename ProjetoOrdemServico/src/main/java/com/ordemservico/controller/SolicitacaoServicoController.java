package com.ordemservico.controller;

import com.ordemservico.model.SolicitacaoServico;
import com.ordemservico.model.StatusSolicitacao;
import com.ordemservico.service.SolicitacaoServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitacoes")
public class SolicitacaoServicoController {

    @Autowired
    private SolicitacaoServicoService solicitacaoServicoService;

    @GetMapping
    public ResponseEntity<List<SolicitacaoServico>> listarTodas() {
        return ResponseEntity.ok(solicitacaoServicoService.listarTodas());
    }

    @GetMapping("/status")
    public ResponseEntity<List<SolicitacaoServico>> listarPorStatus(@RequestParam StatusSolicitacao status) {
        return ResponseEntity.ok(solicitacaoServicoService.listarPorStatus(status));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoServico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoServicoService.buscarPorId(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<SolicitacaoServico> atualizarStatus(@PathVariable Long id, @RequestParam StatusSolicitacao status) {
        return ResponseEntity.ok(solicitacaoServicoService.atualizarStatus(id, status));
    }
}
