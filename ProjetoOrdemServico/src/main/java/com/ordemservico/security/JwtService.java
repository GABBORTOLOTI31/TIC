package com.ordemservico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String segredoJwt;

    @Value("${jwt.expiracao}")
    private long expiracaoMs;

    public String gerarToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiracaoMs))
                .signWith(obterChave(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extrairEmail(String token) {
        return extrairClaims(token).getSubject();
    }

    public boolean validarToken(String token, String email) {
        String emailToken = extrairEmail(token);
        return emailToken.equals(email) && !tokenExpirado(token);
    }

    private Claims extrairClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(obterChave())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean tokenExpirado(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

    private Key obterChave() {
        return Keys.hmacShaKeyFor(segredoJwt.getBytes(StandardCharsets.UTF_8));
    }
}
