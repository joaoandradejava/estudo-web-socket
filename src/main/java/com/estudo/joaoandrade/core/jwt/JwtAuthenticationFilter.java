package com.estudo.joaoandrade.core.jwt;

import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final UsuarioRepository usuarioRepository;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService, UsuarioRepository usuarioRepository) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenJwt = extractTokenFromRequest(request);

        if (tokenJwt == null || !jwtUtil.isTokenValido(tokenJwt)) {
            filterChain.doFilter(request, response); // Se o token for inválido ou inexistente, continue a cadeia de filtros
            return;
        }

        Long userId = jwtUtil.getSubject(tokenJwt);
        Usuario usuario = usuarioRepository.findById(userId).orElse(null);

        if (usuario == null) {
            filterChain.doFilter(request, response); // Se o usuário não existir, continue a cadeia de filtros
            return;
        }

        // Carrega os detalhes do usuário e configura a autenticação
        UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getUsername());
        setAuthenticationInContext(userDetails);

        filterChain.doFilter(request, response); // Continue a cadeia de filtros
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (StringUtils.hasLength(header) && header.startsWith("Bearer ")) {
            return header.substring(7); // Retira o prefixo "Bearer " do token
        }
        return null;
    }

    private void setAuthenticationInContext(UserDetails userDetails) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
