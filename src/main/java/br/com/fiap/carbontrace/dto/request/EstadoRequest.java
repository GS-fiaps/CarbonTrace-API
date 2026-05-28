package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Estado;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastro ou atualização de estado")
public record EstadoRequest(

        @Schema(description = "Nome do estado", example = "São Paulo")
        @NotBlank(message = "O nome do estado é obrigatório")
        @Size(max = 100, message = "O nome do estado deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "Sigla do estado", example = "SP")
        @NotBlank(message = "A sigla do estado é obrigatória")
        @Size(min = 2, max = 2, message = "A sigla do estado deve conter exatamente 2 caracteres")
        String sigla

) {

    public Estado toEntity() {
        return Estado.builder()
                .nome(nome)
                .sigla(sigla.toUpperCase())
                .build();
    }
}