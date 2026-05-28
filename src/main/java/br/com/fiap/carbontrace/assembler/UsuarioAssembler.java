package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.UsuarioController;
import br.com.fiap.carbontrace.dto.response.UsuarioResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UsuarioAssembler {

    public EntityModel<UsuarioResponse> toModel(UsuarioResponse usuario) {
        return EntityModel.of(
                usuario,
                linkTo(methodOn(UsuarioController.class).buscarPorId(usuario.idUsuario())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).listarTodos()).withRel("usuarios"),
                linkTo(methodOn(UsuarioController.class).atualizar(usuario.idUsuario(), null)).withRel("atualizar"),
                linkTo(methodOn(UsuarioController.class).deletar(usuario.idUsuario())).withRel("deletar")
        );
    }
}