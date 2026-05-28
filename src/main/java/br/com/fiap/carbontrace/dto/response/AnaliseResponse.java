package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Analise;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com análise ambiental")
public record AnaliseResponse(

        @Schema(description = "Identificador único da análise", example = "1")
        Long idAnalise,

        @Schema(description = "Data da análise", example = "2026-05-22")
        LocalDate dataAnalise,

        @Schema(description = "Área desmatada em quilômetros quadrados", example = "8.7")
        Double areaDesmatadaKm2,

        @Schema(description = "Percentual de variação identificado", example = "12.5")
        Double percentualVariacao,

        @Schema(description = "Status da análise", example = "ATENCAO")
        StatusAlerta statusAlerta,

        @Schema(description = "ID da imagem satelital analisada", example = "1")
        Long imagemSatelitalId,

        @Schema(description = "Data de captura da imagem analisada", example = "2026-05-20")
        LocalDate dataCapturaImagem,

        @Schema(description = "URL da imagem analisada", example = "https://exemplo.com/imagens/amazonia-2026.jpg")
        String urlImagem,

        @Schema(description = "ID da região analisada", example = "1")
        Long regiaoId,

        @Schema(description = "Nome da região analisada", example = "Amazônia Legal")
        String regiaoNome,

        @Schema(description = "ID do satélite utilizado", example = "1")
        Long sateliteId,

        @Schema(description = "Nome do satélite utilizado", example = "Landsat 8")
        String sateliteNome

) {

    public static AnaliseResponse fromEntity(Analise analise) {
        return new AnaliseResponse(
                analise.getIdAnalise(),
                analise.getDataAnalise(),
                analise.getAreaDesmatadaKm2(),
                analise.getPercentualVariacao(),
                analise.getStatusAlerta(),
                analise.getImagemSatelital().getIdImagem(),
                analise.getImagemSatelital().getDataCaptura(),
                analise.getImagemSatelital().getUrlImagem(),
                analise.getImagemSatelital().getRegiao().getIdRegiao(),
                analise.getImagemSatelital().getRegiao().getNome(),
                analise.getImagemSatelital().getSatelite().getIdSatelite(),
                analise.getImagemSatelital().getSatelite().getNome()
        );
    }
}