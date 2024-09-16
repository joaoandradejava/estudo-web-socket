package com.estudo.joaoandrade.core.security;

import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> obj = this.usuarioRepository.findByUsername(username);

        if (obj.isEmpty()) {
            throw new UsernameNotFoundException(username);
        }

        return new UsuarioLogado(obj.get());
    }
}
