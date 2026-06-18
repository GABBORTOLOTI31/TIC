package com.ordemservico.service;

import com.ordemservico.model.Cliente;
import com.ordemservico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado. ID: " + id));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Cliente salvar(Cliente cliente) {
        if (clienteRepository.existsByCpf(cliente.getCpf())) {
            throw new RuntimeException("Já existe um cliente cadastrado com o CPF: " + cliente.getCpf());
        }
        if (clienteRepository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("Já existe um cliente cadastrado com o e-mail: " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        Cliente clienteExistente = buscarPorId(id);

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());
        clienteExistente.setEmail(clienteAtualizado.getEmail());
        clienteExistente.setEndereco(clienteAtualizado.getEndereco());
        clienteExistente.setDataCadastro(clienteAtualizado.getDataCadastro());

        return clienteRepository.save(clienteExistente);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        clienteRepository.deleteById(id);
    }
}
