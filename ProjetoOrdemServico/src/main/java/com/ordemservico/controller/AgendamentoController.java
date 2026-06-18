package com.ordemservico.controller;

import com.ordemservico.model.Agendamento;
import com.ordemservico.model.StatusAgendamento;
import com.ordemservico.service.AgendamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(agendamentoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Agendamento> salvar(@RequestBody Agendamento agendamento) {
        Agendamento agendamentoSalvo = agendamentoService.salvar(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(agendamentoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento agendamento) {
        return ResponseEntity.ok(agendamentoService.atualizar(id, agendamento));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Agendamento> atualizarStatus(@PathVariable Long id, @RequestParam StatusAgendamento status) {
        return ResponseEntity.ok(agendamentoService.atualizarStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        agendamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
