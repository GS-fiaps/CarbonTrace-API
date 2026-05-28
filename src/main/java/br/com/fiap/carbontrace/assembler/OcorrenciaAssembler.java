package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.OcorrenciaController;
import br.com.fiap.carbontrace.dto.response.OcorrenciaResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class OcorrenciaAssembler {

    public EntityModel<OcorrenciaResponse> toModel(OcorrenciaResponse ocorrencia) {
        return EntityModel.of(
                ocorrencia,
                linkTo(methodOn(OcorrenciaController.class).buscarPorId(ocorrencia.idOcorrencia())).withSelfRel(),
                linkTo(methodOn(OcorrenciaController.class).listarTodos()).withRel("ocorrencias"),
                linkTo(methodOn(OcorrenciaController.class).atualizar(ocorrencia.idOcorrencia(), null)).withRel("atualizar"),
                linkTo(methodOn(OcorrenciaController.class).deletar(ocorrencia.idOcorrencia())).withRel("deletar")
        );
    }
}