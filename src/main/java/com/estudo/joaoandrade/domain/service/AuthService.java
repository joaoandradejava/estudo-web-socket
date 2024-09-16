package com.estudo.joaoandrade.domain.service;

import com.estudo.joaoandrade.api.model.AutenticacaoSucedidaModel;
import com.estudo.joaoandrade.core.jwt.JwtUtil;
import com.estudo.joaoandrade.domain.exception.FalhaAutenticacaoException;
import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private JwtUtil jwtUtil;

    public AutenticacaoSucedidaModel autenticar(String username, String senha) {
        Usuario usuario = usuarioRepository.findByUsername(username).orElseThrow(() -> new FalhaAutenticacaoException(this.messageSource.getMessage("falha.autenticacao.nome.usuario", null, LocaleContextHolder.getLocale())));
        if (!usuario.isSenhaCorreta(senha, passwordEncoder)) {
            throw new FalhaAutenticacaoException(this.messageSource.getMessage("falha.autenticacao.senha.incorreta", null, LocaleContextHolder.getLocale()));
        }

        String tokenJwt = String.format("Bearer %s", this.jwtUtil.gerarToken(usuario.getId()));
        AutenticacaoSucedidaModel autenticacaoSucedidaModel = new AutenticacaoSucedidaModel();
        autenticacaoSucedidaModel.setId(usuario.getId());
        autenticacaoSucedidaModel.setNome(usuario.getNome());
        autenticacaoSucedidaModel.setTokenJwt(tokenJwt);

        return autenticacaoSucedidaModel;
    }
}
