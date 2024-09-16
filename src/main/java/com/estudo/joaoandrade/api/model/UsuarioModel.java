package com.estudo.joaoandrade.api.model;

import com.estudo.joaoandrade.domain.model.enumeration.Permissao;

import java.util.Set;

public class UsuarioModel {
    private Long id;
    private String nome;

    private String email;

    private String username;

    private String senha;

    private Boolean isAtivo;

    private Set<Permissao> permissao;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return isAtivo;
    }

    public void setAtivo(Boolean ativo) {
        isAtivo = ativo;
    }

    public Set<Permissao> getPermissao() {
        return permissao;
    }

    public void setPermissao(Set<Permissao> permissao) {
        this.permissao = permissao;
    }
}
