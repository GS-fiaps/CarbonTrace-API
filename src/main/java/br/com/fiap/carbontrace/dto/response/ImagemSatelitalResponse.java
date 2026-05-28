package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.ImagemSatelital;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com imagem satelital")
public record ImagemSatelitalResponse(

        @Schema(description = "Identificador único da imagem satelital", example = "1")
        Long idImagem,

        @Schema(description = "Data em que a imagem foi capturada", example = "2026-05-20")
        LocalDate dataCaptura,

        @Schema(description = "Resolução da imagem em metros", example = "10.0")
        Double resolucaoMetros,

        @Schema(description = "URL de acesso à imagem", example = "https://exemplo.com/imagens/amazonia-2026.jpg")
        String urlImagem,

        @Schema(description = "ID da região vinculada", example = "1")
        Long regiaoId,

        @Schema(description = "Nome da região vinculada", example = "Amazônia Legal")
        String regiaoNome,

        @Schema(description = "ID do satélite vinculado", example = "1")
        Long sateliteId,

        @Schema(description = "Nome do satélite vinculado", example = "Landsat 8")
        String sateliteNome,

        @Schema(description = "Agência responsável pelo satélite", example = "NASA")
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