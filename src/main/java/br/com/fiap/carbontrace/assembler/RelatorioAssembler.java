package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.RelatorioController;
import br.com.fiap.carbontrace.dto.response.RelatorioResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class RelatorioAssembler {

    public EntityModel<RelatorioResponse> toModel(RelatorioResponse relatorio) {
        return EntityModel.of(
                relatorio,
                linkTo(methodOn(RelatorioController.class).buscarPorId(relatorio.idRelatorio())).withSelfRel(),
                linkTo(methodOn(RelatorioController.class).listarTodos()).withRel("relatorios"),
                linkTo(methodOn(RelatorioController.class).atualizar(relatorio.idRelatorio(), null)).withRel("atualizar"),
                linkTo(methodOn(RelatorioController.class).deletar(relatorio.idRelatorio())).withRel("deletar")
        );
    }
}