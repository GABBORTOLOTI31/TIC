package com.ordemservico.service;

import com.ordemservico.model.Contato;
import com.ordemservico.repository.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public Contato salvar(Contato contato) {
        return contatoRepository.save(contato);
    }

    public List<Contato> listarTodos() {
        return contatoRepository.findByOrderByDataEnvioDesc();
    }

    public Contato buscarPorId(Long id) {
        return contatoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contato não encontrado. ID: " + id));
    }

    public void deletar(Long id) {
        buscarPorId(id);
        contatoRepository.deleteById(id);
    }
}
