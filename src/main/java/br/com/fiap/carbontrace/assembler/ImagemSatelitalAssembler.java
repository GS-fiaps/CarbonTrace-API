package br.com.fiap.carbontrace.assembler;

import br.com.fiap.carbontrace.controller.ImagemSatelitalController;
import br.com.fiap.carbontrace.dto.response.ImagemSatelitalResponse;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ImagemSatelitalAssembler {

    public EntityModel<ImagemSatelitalResponse> toModel(ImagemSatelitalResponse imagem) {
        return EntityModel.of(
                imagem,
                linkTo(methodOn(ImagemSatelitalController.class).buscarPorId(imagem.idImagem())).withSelfRel(),
                linkTo(methodOn(ImagemSatelitalController.class).listarTodos()).withRel("imagens-satelitais"),
                linkTo(methodOn(ImagemSatelitalController.class).atualizar(imagem.idImagem(), null)).withRel("atualizar"),
                linkTo(methodOn(ImagemSatelitalController.class).deletar(imagem.idImagem())).withRel("deletar")
        );
    }
}