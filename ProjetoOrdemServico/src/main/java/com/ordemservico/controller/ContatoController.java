package com.ordemservico.controller;

import com.ordemservico.model.Contato;
import com.ordemservico.service.ContatoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contatos")
public class ContatoController {

    @Autowired
    private ContatoService contatoService;

    @GetMapping
    public ResponseEntity<List<Contato>> listarTodos() {
        return ResponseEntity.ok(contatoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(contatoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        contatoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
