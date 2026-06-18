package com.ordemservico.controller;

import com.ordemservico.model.Servico;
import com.ordemservico.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        return ResponseEntity.ok(servicoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Servico>> listarAtivos() {
        return ResponseEntity.ok(servicoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.buscarPorId(id));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Servico>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(servicoService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Servico> salvar(@RequestBody Servico servico) {
        Servico servicoSalvo = servicoService.salvar(servico);
        return ResponseEntity.status(HttpStatus.CREATED).body(servicoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servico> atualizar(@PathVariable Long id, @RequestBody Servico servico) {
        return ResponseEntity.ok(servicoService.atualizar(id, servico));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Servico> ativarDesativar(@PathVariable Long id) {
        return ResponseEntity.ok(servicoService.ativarDesativar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        servicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
