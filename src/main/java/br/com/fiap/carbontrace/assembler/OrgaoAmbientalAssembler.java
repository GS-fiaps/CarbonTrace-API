package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.OrgaoAmbientalController;
import br.com.fiap.carbontrace.dto.response.OrgaoAmbientalResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OrgaoAmbientalAssembler {

    public EntityModel<OrgaoAmbientalResponse> toModel(OrgaoAmbientalResponse orgaoAmbiental) {
        return EntityModel.of(
                orgaoAmbiental,
                linkTo(methodOn(OrgaoAmbientalController.class).buscarPorId(orgaoAmbiental.idOrgao())).withSelfRel(),
                linkTo(methodOn(OrgaoAmbientalController.class).listarTodos()).withRel("orgaos-ambientais"),
                linkTo(methodOn(OrgaoAmbientalController.class).atualizar(orgaoAmbiental.idOrgao(), null)).withRel("atualizar"),
                linkTo(methodOn(OrgaoAmbientalController.class).deletar(orgaoAmbiental.idOrgao())).withRel("deletar")
        );
    }
}