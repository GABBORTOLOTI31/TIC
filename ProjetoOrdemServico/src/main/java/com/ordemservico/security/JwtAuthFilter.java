package com.ordemservico.security;

import com.ordemservico.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String cabecalhoAutorizacao = request.getHeader("Authorization");

        if (cabecalhoAutorizacao == null || !cabecalhoAutorizacao.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = cabecalhoAutorizacao.substring(7);
        String email = jwtService.extrairEmail(token);

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            usuarioRepository.findByEmail(email).ifPresent(usuario -> {
                if (jwtService.validarToken(token, email)) {
                    var authorities = usuario.getAuthorities().stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken autenticacao =
                            new UsernamePasswordAuthenticationToken(email, null, authorities);

                    autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(autenticacao);
                }
            });
        }

        filterChain.doFilter(request, response);
    }
}
