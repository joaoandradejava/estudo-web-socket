package com.estudo.joaoandrade.core.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("jwt")
public class JwtConfigProperty {
    private String senha;
    private Long tempoExpiracao;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getTempoExpiracao() {
        return tempoExpiracao;
    }

    public void setTempoExpiracao(Long tempoExpiracao) {
        this.tempoExpiracao = tempoExpiracao;
    }
}
