package com.estudo.joaoandrade.api.model;

import jakarta.validation.constraints.NotBlank;

public class LoginInput {

    @NotBlank
    private String username;

    @NotBlank
    private String senha;

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getSenha() {
        return senha;
    }

    public void setSenha(@NotBlank String senha) {
        this.senha = senha;
    }
}
