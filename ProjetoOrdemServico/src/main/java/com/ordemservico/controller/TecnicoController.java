package com.ordemservico.controller;

import com.ordemservico.model.Tecnico;
import com.ordemservico.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping
    public ResponseEntity<List<Tecnico>> listarTodos() {
        return ResponseEntity.ok(tecnicoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Tecnico>> listarAtivos() {
        return ResponseEntity.ok(tecnicoService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.buscarPorId(id));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Tecnico>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(tecnicoService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Tecnico> salvar(@RequestBody Tecnico tecnico) {
        Tecnico tecnicoSalvo = tecnicoService.salvar(tecnico);
        return ResponseEntity.status(HttpStatus.CREATED).body(tecnicoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tecnico> atualizar(@PathVariable Long id, @RequestBody Tecnico tecnico) {
        return ResponseEntity.ok(tecnicoService.atualizar(id, tecnico));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Tecnico> ativarDesativar(@PathVariable Long id) {
        return ResponseEntity.ok(tecnicoService.ativarDesativar(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        tecnicoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
