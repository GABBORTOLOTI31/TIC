package com.ordemservico.service;

import com.ordemservico.model.Perfil;
import com.ordemservico.model.Usuario;
import com.ordemservico.repository.UsuarioRepository;
import com.ordemservico.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciais inválidas."));

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais inválidas.");
        }

        return jwtService.gerarToken(usuario.getEmail());
    }

    public Usuario registrar(String nome, String email, String senha, Perfil perfil) {
        if (usuarioRepository.existsByEmail(email)) {
            throw new RuntimeException("E-mail já cadastrado: " + email);
        }

        Usuario novoUsuario = new Usuario(email, passwordEncoder.encode(senha), nome, perfil);
        return usuarioRepository.save(novoUsuario);
    }
}
