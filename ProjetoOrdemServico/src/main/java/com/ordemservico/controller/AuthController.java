package com.ordemservico.controller;

import com.ordemservico.model.Perfil;
import com.ordemservico.model.Usuario;
import com.ordemservico.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> credenciais) {
        String email = credenciais.get("email");
        String senha = credenciais.get("senha");

        String token = authService.autenticar(email, senha);

        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/registrar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Map<String, String> dados) {
        String nome = dados.get("nome");
        String email = dados.get("email");
        String senha = dados.get("senha");
        Perfil perfil = Perfil.valueOf(dados.getOrDefault("perfil", "ATENDENTE").toUpperCase());

        Usuario usuario = authService.registrar(nome, email, senha, perfil);

        return ResponseEntity.ok(Map.of(
                "mensagem", "Usuário registrado com sucesso.",
                "id", usuario.getId(),
                "email", usuario.getEmail(),
                "perfil", usuario.getPerfil()
        ));
    }
}
