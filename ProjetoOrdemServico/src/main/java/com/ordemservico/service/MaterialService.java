package com.ordemservico.service;

import com.ordemservico.model.Material;
import com.ordemservico.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    public Material buscarPorId(Long id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material não encontrado. ID: " + id));
    }

    public List<Material> buscarPorDescricao(String descricao) {
        return materialRepository.findByDescricaoContainingIgnoreCase(descricao);
    }

    public Material salvar(Material material) {
        return materialRepository.save(material);
    }

    public Material atualizar(Long id, Material materialAtualizado) {
        Material materialExistente = buscarPorId(id);

        materialExistente.setDescricao(materialAtualizado.getDescricao());
        materialExistente.setUnidade(materialAtualizado.getUnidade());
        materialExistente.setPrecoUnitario(materialAtualizado.getPrecoUnitario());

        return materialRepository.save(materialExistente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        materialRepository.deleteById(id);
    }
}
