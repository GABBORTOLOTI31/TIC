package com.ordemservico.controller;

import com.ordemservico.model.Material;
import com.ordemservico.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiais")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> listarTodos() {
        return ResponseEntity.ok(materialService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.buscarPorId(id));
    }

    @GetMapping("/busca")
    public ResponseEntity<List<Material>> buscarPorDescricao(@RequestParam String descricao) {
        return ResponseEntity.ok(materialService.buscarPorDescricao(descricao));
    }

    @PostMapping
    public ResponseEntity<Material> salvar(@RequestBody Material material) {
        Material materialSalvo = materialService.salvar(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(materialSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Material> atualizar(@PathVariable Long id, @RequestBody Material material) {
        return ResponseEntity.ok(materialService.atualizar(id, material));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        materialService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
