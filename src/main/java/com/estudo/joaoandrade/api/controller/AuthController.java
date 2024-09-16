package com.estudo.joaoandrade.api.controller;

import com.estudo.joaoandrade.api.model.AutenticacaoSucedidaModel;
import com.estudo.joaoandrade.api.model.LoginInput;
import com.estudo.joaoandrade.core.jwt.JwtUtil;
import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public AutenticacaoSucedidaModel autenticar(@RequestBody @Valid LoginInput loginInput) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginInput.getUsername(), loginInput.getSenha()));
            Usuario usuario = this.usuarioRepository.findByUsername(loginInput.getUsername()).get();
            AutenticacaoSucedidaModel autenticacao = new AutenticacaoSucedidaModel();
            autenticacao.setId(usuario.getId());
            autenticacao.setNome(usuario.getNome());
            autenticacao.setTokenJwt(String.format("Bearer %s", this.jwtUtil.gerarToken(usuario.getId())));

            return autenticacao;
        } catch (AuthenticationException e) {
            return null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
