package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Satelite;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record SateliteRequest(

        @NotBlank(message = "O nome do satélite é obrigatório")
        @Size(max = 100, message = "O nome do satélite deve ter no máximo 100 caracteres")
        String nome,

        @NotBlank(message = "A agência responsável é obrigatória")
        @Size(max = 100, message = "A agência deve ter no máximo 100 caracteres")
        String agencia,

        @NotNull(message = "A altitude em km é obrigatória")
        @Positive(message = "A altitude em km deve ser maior que zero")
        Double altitudeKm,

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