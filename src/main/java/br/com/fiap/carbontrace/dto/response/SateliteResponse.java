package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Satelite;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados após operações com satélite")
public record SateliteResponse(

        @Schema(description = "Identificador único do satélite", example = "1")
        Long idSatelite,

        @Schema(description = "Nome do satélite", example = "Landsat 8")
        String nome,

        @Schema(description = "Agência responsável pelo satélite", example = "NASA")
        String agencia,

        @Schema(description = "Altitude orbital em quilômetros", example = "705.0")
        Double altitudeKm,

        @Schema(description = "Ano de lançamento do satélite", example = "2013")
        Integer anoLancamento

) {

    public static SateliteResponse fromEntity(Satelite satelite) {
        return new SateliteResponse(
                satelite.getIdSatelite(),
                satelite.getNome(),
                satelite.getAgencia(),
                satelite.getAltitudeKm(),
                satelite.getAnoLancamento()
        );
    }
}