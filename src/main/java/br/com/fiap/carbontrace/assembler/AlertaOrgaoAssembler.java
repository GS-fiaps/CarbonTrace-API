package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.AlertaOrgaoController;
import br.com.fiap.carbontrace.dto.response.AlertaOrgaoResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AlertaOrgaoAssembler {

    public EntityModel<AlertaOrgaoResponse> toModel(AlertaOrgaoResponse alertaOrgao) {
        return EntityModel.of(
                alertaOrgao,
                linkTo(methodOn(AlertaOrgaoController.class)
                        .buscarPorId(alertaOrgao.alertaId(), alertaOrgao.orgaoAmbientalId()))
                        .withSelfRel(),

                linkTo(methodOn(AlertaOrgaoController.class)
                        .listarTodos())
                        .withRel("alertas-orgaos"),

                linkTo(methodOn(AlertaOrgaoController.class)
                        .atualizar(alertaOrgao.alertaId(), alertaOrgao.orgaoAmbientalId(), null))
                        .withRel("atualizar"),

                linkTo(methodOn(AlertaOrgaoController.class)
                        .deletar(alertaOrgao.alertaId(), alertaOrgao.orgaoAmbientalId()))
                        .withRel("deletar")
        );
    }
}