package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.Regiao;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados necessários para cadastro ou atualização de região ambiental monitorada")
public record RegiaoRequest(

        @Schema(description = "Nome da região monitorada", example = "Amazônia Legal")
        @NotBlank(message = "O nome da região é obrigatório")
        @Size(max = 150, message = "O nome da região deve ter no máximo 150 caracteres")
        String nome,

        @Schema(description = "Latitude da região", example = "-3.4653")
        @NotNull(message = "A latitude é obrigatória")
        @DecimalMin(value = "-90.0", message = "A latitude deve ser maior ou igual a -90")
        @DecimalMax(value = "90.0", message = "A latitude deve ser menor ou igual a 90")
        Double latitude,

        @Schema(description = "Longitude da região", example = "-62.2159")
        @NotNull(message = "A longitude é obrigatória")
        @DecimalMin(value = "-180.0", message = "A longitude deve ser maior ou igual a -180")
        @DecimalMax(value = "180.0", message = "A longitude deve ser menor ou igual a 180")
        Double longitude,

        @Schema(description = "Área da região em quilômetros quadrados", example = "5000000.0")
        @NotNull(message = "A área em km² é obrigatória")
        @Positive(message = "A área em km² deve ser maior que zero")
        Double areaKm2,

        @Schema(description = "ID do estado ao qual a região pertence", example = "1")
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