package com.ordemservico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
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
                .claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiracaoMs))
                .signWith(obterChave())
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
        return Jwts.parser()
                .verifyWith(obterChave())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean tokenExpirado(String token) {
        return extrairClaims(token).getExpiration().before(new Date());
    }

    private SecretKey obterChave() {
        return Keys.hmacShaKeyFor(segredoJwt.getBytes(StandardCharsets.UTF_8));
    }
}
