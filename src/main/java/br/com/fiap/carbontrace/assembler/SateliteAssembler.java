package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.SateliteController;
import br.com.fiap.carbontrace.dto.response.SateliteResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class SateliteAssembler {

    public EntityModel<SateliteResponse> toModel(SateliteResponse satelite) {
        return EntityModel.of(
                satelite,
                linkTo(methodOn(SateliteController.class).buscarPorId(satelite.idSatelite())).withSelfRel(),
                linkTo(methodOn(SateliteController.class).listarTodos()).withRel("satelites"),
                linkTo(methodOn(SateliteController.class).atualizar(satelite.idSatelite(), null)).withRel("atualizar"),
                linkTo(methodOn(SateliteController.class).deletar(satelite.idSatelite())).withRel("deletar")
        );
    }
}