package com.estudo.joaoandrade.api.disassembler;


import com.estudo.joaoandrade.api.model.UsuarioInput;
import com.estudo.joaoandrade.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Usuario toDomainModel(UsuarioInput usuarioInput) {
        return this.modelMapper.map(usuarioInput, Usuario.class);
    }

    public void copyToDomainModel(UsuarioInput usuarioInput, Usuario usuario) {
        this.modelMapper.map(usuarioInput, usuario);
    }

}
