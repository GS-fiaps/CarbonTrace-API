package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.ImagemSatelital;

import java.time.LocalDate;

public record ImagemSatelitalResponse(
        Long idImagem,
        LocalDate dataCaptura,
        Double resolucaoMetros,
        String urlImagem,

        Long regiaoId,
        String regiaoNome,

        Long sateliteId,
        String sateliteNome,
        String sateliteAgencia
) {

    public static ImagemSatelitalResponse fromEntity(ImagemSatelital imagemSatelital) {
        return new ImagemSatelitalResponse(
                imagemSatelital.getIdImagem(),
                imagemSatelital.getDataCaptura(),
                imagemSatelital.getResolucaoMetros(),
                imagemSatelital.getUrlImagem(),

                imagemSatelital.getRegiao().getIdRegiao(),
                imagemSatelital.getRegiao().getNome(),

                imagemSatelital.getSatelite().getIdSatelite(),
                imagemSatelital.getSatelite().getNome(),
                imagemSatelital.getSatelite().getAgencia()
        );
    }
}