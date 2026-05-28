package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.EstadoController;
import br.com.fiap.carbontrace.dto.response.EstadoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class EstadoAssembler {

    public EntityModel<EstadoResponse> toModel(EstadoResponse estado) {
        return EntityModel.of(
                estado,
                linkTo(methodOn(EstadoController.class).buscarPorId(estado.idEstado())).withSelfRel(),
                linkTo(methodOn(EstadoController.class).listarTodos()).withRel("estados"),
                linkTo(methodOn(EstadoController.class).atualizar(estado.idEstado(), null)).withRel("atualizar"),
                linkTo(methodOn(EstadoController.class).deletar(estado.idEstado())).withRel("deletar")
        );
    }
}