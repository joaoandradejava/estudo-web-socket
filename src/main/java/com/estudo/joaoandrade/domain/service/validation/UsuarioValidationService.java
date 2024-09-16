package com.estudo.joaoandrade.domain.service.validation;

import com.estudo.joaoandrade.domain.exception.NegocioException;
import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UsuarioValidationService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarEmailUnico(Usuario usuario) {
        if (usuario == null || usuario.getEmail() == null) {
            return;
        }

        this.usuarioRepository.findByEmail(usuario.getEmail()).ifPresent(existingUser -> {
            if (!Objects.equals(existingUser.getId(), usuario.getId())) {
                throw new NegocioException("Já existe um usuário com este email.");
            }
        });
    }

    public void validarUsuarioUnico(Usuario usuario) {
        if (usuario == null || usuario.getUsername() == null) {
            return;
        }

        this.usuarioRepository.findByUsername(usuario.getUsername()).ifPresent(existingUser -> {
            if (!Objects.equals(existingUser.getId(), usuario.getId())) {
                throw new NegocioException("Já existe um usuário com este email.");
            }
        });
    }

}
