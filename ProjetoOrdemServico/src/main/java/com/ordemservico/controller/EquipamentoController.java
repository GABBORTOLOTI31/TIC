package com.ordemservico.controller;

import com.ordemservico.model.Equipamento;
import com.ordemservico.service.EquipamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    @GetMapping
    public ResponseEntity<List<Equipamento>> listarTodos() {
        return ResponseEntity.ok(equipamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(equipamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Equipamento> salvar(@RequestBody Equipamento equipamento) {
        Equipamento equipamentoSalvo = equipamentoService.salvar(equipamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(equipamentoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable Long id, @RequestBody Equipamento equipamento) {
        return ResponseEntity.ok(equipamentoService.atualizar(id, equipamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        equipamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
