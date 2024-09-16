package com.estudo.joaoandrade.api.assembler;

import com.estudo.joaoandrade.api.model.UsuarioModel;
import com.estudo.joaoandrade.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario) {
        return this.modelMapper.map(usuario, UsuarioModel.class);
    }

}
