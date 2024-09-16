package com.estudo.joaoandrade.domain.model.enumeration;

public enum Permissao {
    ADMIN("ROLE_ADMIN"), COMUM("ROLE_COMUM");

    private final String role;

    Permissao(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
