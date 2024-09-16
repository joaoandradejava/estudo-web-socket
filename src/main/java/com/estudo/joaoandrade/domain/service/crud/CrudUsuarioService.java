package com.estudo.joaoandrade.domain.service.crud;

import com.estudo.joaoandrade.domain.exception.EntidadeNaoEncontradaException;
import com.estudo.joaoandrade.domain.filter.UsuarioFilter;
import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import com.estudo.joaoandrade.domain.service.validation.UsuarioValidationService;
import com.estudo.joaoandrade.domain.specification.UsuarioSpecificator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CrudUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private UsuarioValidationService usuarioValidationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Page<Usuario> buscarTodos(UsuarioFilter usuarioFilter, Pageable pageable) {
        return this.usuarioRepository.findAll(UsuarioSpecificator.find(usuarioFilter), pageable);
    }

    public Usuario buscarPorId(Long id) {
        return this.usuarioRepository.findById(id).orElseThrow(() -> new EntidadeNaoEncontradaException(this.messageSource.getMessage("entidade.nao.encontrado.sistema.id", new Object[]{"usuário", id}, LocaleContextHolder.getLocale())));
    }

    @Transactional
    public Usuario cadastrar(Usuario usuario) {
        this.validacoes(usuario);
        usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));

        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizar(Usuario usuario) {
        this.validacoes(usuario);

        return this.usuarioRepository.save(usuario);
    }

    private void validacoes(Usuario usuario) {
        this.usuarioValidationService.validarEmailUnico(usuario);
        this.usuarioValidationService.validarUsuarioUnico(usuario);
    }

    @Transactional
    public void deletarPorId(Long id) {
        this.buscarPorId(id);

        try {
            this.usuarioRepository.deleteById(id);
            this.usuarioRepository.flush();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }
    }

}
