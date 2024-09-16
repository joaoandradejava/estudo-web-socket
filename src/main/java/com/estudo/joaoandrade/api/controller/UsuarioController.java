package com.estudo.joaoandrade.api.controller;

import com.estudo.joaoandrade.api.assembler.UsuarioModelAssembler;
import com.estudo.joaoandrade.api.disassembler.UsuarioInputDisassembler;
import com.estudo.joaoandrade.api.model.UsuarioInput;
import com.estudo.joaoandrade.api.model.UsuarioModel;
import com.estudo.joaoandrade.domain.filter.UsuarioFilter;
import com.estudo.joaoandrade.domain.model.Usuario;
import com.estudo.joaoandrade.domain.service.crud.CrudUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CrudUsuarioService crudUsuarioService;

    @Autowired
    private UsuarioModelAssembler usuarioModelAssembler;

    @Autowired
    private UsuarioInputDisassembler usuarioInputDisassembler;

    @GetMapping
    public Page<UsuarioModel> buscarTodos(UsuarioFilter usuarioFilter, Pageable pageable) {
        Page<Usuario> page = this.crudUsuarioService.buscarTodos(usuarioFilter, pageable);

        return page.map(u -> usuarioModelAssembler.toModel(u));
    }

    @GetMapping("/{id}")
    public UsuarioModel buscarPorId(Long id) {
        Usuario usuario = this.crudUsuarioService.buscarPorId(id);

        return this.usuarioModelAssembler.toModel(usuario);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UsuarioModel cadastrar(@RequestBody @Valid UsuarioInput usuarioInput) {
        Usuario usuario = this.crudUsuarioService.cadastrar(this.usuarioInputDisassembler.toDomainModel(usuarioInput));

        return this.usuarioModelAssembler.toModel(usuario);
    }

    @PutMapping("/{id}")
    public UsuarioModel atualizar(@RequestBody @Valid UsuarioInput usuarioInput, @PathVariable Long id) {
        Usuario usuario = this.crudUsuarioService.buscarPorId(id);
        this.usuarioInputDisassembler.copyToDomainModel(usuarioInput, usuario);
        usuario = this.crudUsuarioService.atualizar(usuario);

        return this.usuarioModelAssembler.toModel(usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletarPorId(@PathVariable Long id) {
        this.crudUsuarioService.deletarPorId(id);
    }
}
