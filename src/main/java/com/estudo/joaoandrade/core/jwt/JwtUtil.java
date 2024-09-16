package com.estudo.joaoandrade.core.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    @Autowired
    private JwtConfigProperty jwtConfigProperty;

    public String gerarToken(Long id) {
        return Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + jwtConfigProperty.getTempoExpiracao())).subject(id.toString()).signWith(SignatureAlgorithm.HS512, this.jwtConfigProperty.getSenha()).compact();
    }

    public Long getSubject(String tokenJwt) {
        return isTokenValido(tokenJwt) ? Long.parseLong(getClaims(tokenJwt).getSubject()) : null;
    }

    public boolean isTokenValido(String token) {
        Claims claims = getClaims(token);
        if (claims == null) return false;
        Date now = new Date();
        Date expiration = claims.getExpiration();
        String subject = claims.getSubject();
        boolean isTokenExpirado = expiration == null || expiration.after(now);
        return subject != null && !isTokenExpirado;
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser().setSigningKey(this.jwtConfigProperty.getSenha()).build().parseClaimsJws(token).getBody();
        } catch (Exception e) {
            return null;
        }
    }

}
