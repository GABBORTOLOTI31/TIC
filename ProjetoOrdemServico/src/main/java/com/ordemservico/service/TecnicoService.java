package com.ordemservico.service;

import com.ordemservico.model.Tecnico;
import com.ordemservico.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public List<Tecnico> listarTodos() {
        return tecnicoRepository.findAll();
    }

    public List<Tecnico> listarAtivos() {
        return tecnicoRepository.findByAtivo(true);
    }

    public Tecnico buscarPorId(Long id) {
        return tecnicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Técnico não encontrado. ID: " + id));
    }

    public List<Tecnico> buscarPorNome(String nome) {
        return tecnicoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Tecnico salvar(Tecnico tecnico) {
        if (tecnicoRepository.existsByCpf(tecnico.getCpf())) {
            throw new RuntimeException("Já existe um técnico cadastrado com o CPF: " + tecnico.getCpf());
        }
        if (tecnicoRepository.existsByNrRegistro(tecnico.getNrRegistro())) {
            throw new RuntimeException("Já existe um técnico cadastrado com o registro: " + tecnico.getNrRegistro());
        }
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico atualizar(Long id, Tecnico tecnicoAtualizado) {
        Tecnico tecnicoExistente = buscarPorId(id);

        tecnicoExistente.setNome(tecnicoAtualizado.getNome());
        tecnicoExistente.setTelefone(tecnicoAtualizado.getTelefone());
        tecnicoExistente.setEmail(tecnicoAtualizado.getEmail());
        tecnicoExistente.setEspecialidade(tecnicoAtualizado.getEspecialidade());
        tecnicoExistente.setNrRegistro(tecnicoAtualizado.getNrRegistro());

        return tecnicoRepository.save(tecnicoExistente);
    }

    public Tecnico ativarDesativar(Long id) {
        Tecnico tecnico = buscarPorId(id);
        tecnico.setAtivo(!tecnico.isAtivo());
        return tecnicoRepository.save(tecnico);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        tecnicoRepository.deleteById(id);
    }
}
