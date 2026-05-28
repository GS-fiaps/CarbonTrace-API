package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.AlertaController;
import br.com.fiap.carbontrace.dto.response.AlertaResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AlertaAssembler {

    public EntityModel<AlertaResponse> toModel(AlertaResponse alerta) {
        return EntityModel.of(
                alerta,
                linkTo(methodOn(AlertaController.class).buscarPorId(alerta.idAlerta())).withSelfRel(),
                linkTo(methodOn(AlertaController.class).listarTodos()).withRel("alertas"),
                linkTo(methodOn(AlertaController.class).atualizar(alerta.idAlerta(), null)).withRel("atualizar"),
                linkTo(methodOn(AlertaController.class).deletar(alerta.idAlerta())).withRel("deletar")
        );
    }
}