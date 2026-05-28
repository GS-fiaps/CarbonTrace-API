package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Satelite;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados necessários para cadastro ou atualização de satélite")
public record SateliteRequest(

        @Schema(description = "Nome do satélite", example = "Landsat 8")
        @NotBlank(message = "O nome do satélite é obrigatório")
        @Size(max = 100, message = "O nome do satélite deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "Agência responsável pelo satélite", example = "NASA")
        @NotBlank(message = "A agência responsável é obrigatória")
        @Size(max = 100, message = "A agência deve ter no máximo 100 caracteres")
        String agencia,

        @Schema(description = "Altitude orbital do satélite em quilômetros", example = "705.0")
        @NotNull(message = "A altitude em km é obrigatória")
        @Positive(message = "A altitude em km deve ser maior que zero")
        Double altitudeKm,

        @Schema(description = "Ano de lançamento do satélite", example = "2013")
        @NotNull(message = "O ano de lançamento é obrigatório")
        @Min(value = 1957, message = "O ano de lançamento deve ser igual ou maior que 1957")
        @Max(value = 2100, message = "O ano de lançamento deve ser válido")
        Integer anoLancamento

) {

    public Satelite toEntity() {
        return Satelite.builder()
                .nome(nome)
                .agencia(agencia)
                .altitudeKm(altitudeKm)
                .anoLancamento(anoLancamento)
                .build();
    }
}