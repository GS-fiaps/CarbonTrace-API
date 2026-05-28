package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.AnaliseController;
import br.com.fiap.carbontrace.dto.response.AnaliseResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AnaliseAssembler {

    public EntityModel<AnaliseResponse> toModel(AnaliseResponse analise) {
        return EntityModel.of(
                analise,
                linkTo(methodOn(AnaliseController.class).buscarPorId(analise.idAnalise())).withSelfRel(),
                linkTo(methodOn(AnaliseController.class).listarTodos()).withRel("analises"),
                linkTo(methodOn(AnaliseController.class).atualizar(analise.idAnalise(), null)).withRel("atualizar"),
                linkTo(methodOn(AnaliseController.class).deletar(analise.idAnalise())).withRel("deletar")
        );
    }
}