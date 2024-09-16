package com.estudo.joaoandrade.api.controller;

import com.estudo.joaoandrade.api.model.AutenticacaoSucedidaModel;
import com.estudo.joaoandrade.api.model.LoginInput;
import com.estudo.joaoandrade.core.jwt.JwtUtil;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import com.estudo.joaoandrade.domain.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthService authService;

    @PostMapping
    public AutenticacaoSucedidaModel autenticar(@RequestBody @Valid LoginInput loginInput) {
        return this.authService.autenticar(loginInput.getUsername(), loginInput.getSenha());
    }
}
