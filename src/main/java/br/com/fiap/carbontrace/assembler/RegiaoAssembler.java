package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.RegiaoController;
import br.com.fiap.carbontrace.dto.response.RegiaoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RegiaoAssembler {

    public EntityModel<RegiaoResponse> toModel(RegiaoResponse regiao) {
        return EntityModel.of(
                regiao,
                linkTo(methodOn(RegiaoController.class).buscarPorId(regiao.idRegiao())).withSelfRel(),
                linkTo(methodOn(RegiaoController.class).listarTodos()).withRel("regioes"),
                linkTo(methodOn(RegiaoController.class).atualizar(regiao.idRegiao(), null)).withRel("atualizar"),
                linkTo(methodOn(RegiaoController.class).deletar(regiao.idRegiao())).withRel("deletar")
        );
    }
}