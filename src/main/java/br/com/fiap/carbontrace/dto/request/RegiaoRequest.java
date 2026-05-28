package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.Regiao;
import jakarta.validation.constraints.*;

public record RegiaoRequest(

        @NotBlank(message = "O nome da região é obrigatório")
        @Size(max = 100, message = "O nome da região deve ter no máximo 100 caracteres")
        String nome,

        @NotNull(message = "A latitude é obrigatória")
        @DecimalMin(value = "-90.0", message = "A latitude deve ser maior ou igual a -90")
        @DecimalMax(value = "90.0", message = "A latitude deve ser menor ou igual a 90")
        Double latitude,

        @NotNull(message = "A longitude é obrigatória")
        @DecimalMin(value = "-180.0", message = "A longitude deve ser maior ou igual a -180")
        @DecimalMax(value = "180.0", message = "A longitude deve ser menor ou igual a 180")
        Double longitude,

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
                .longitude(longitude)
                .areaKm2(areaKm2)
                .estado(estado)
                .build();
    }
}