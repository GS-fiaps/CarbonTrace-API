package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.Regiao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record RegiaoRequest(

        @NotBlank(message = "O nome da região é obrigatório")
        @Size(max = 100, message = "O nome da região deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A latitude é obrigatória")
        Double latitude,

        @NotNull(message = "A área em km² é obrigatória")
        @Positive(message = "A área em km² deve ser maior que zero")
        Double areaKm2,

        @NotNull(message = "O ID do estado é obrigatório")
        Long estadoId

) {

    public Regiao toEntity(Estado estado) {
        return Regiao.builder()
                .nome(nome)
                .latitude(latitude)
                .areaKm2(areaKm2)
                .estado(estado)
                .build();
    }
}