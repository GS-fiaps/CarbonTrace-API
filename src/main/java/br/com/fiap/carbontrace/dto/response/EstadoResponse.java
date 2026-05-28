package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Estado;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados após operações com estado")
public record EstadoResponse(

        @Schema(description = "Identificador único do estado", example = "1")
        Long idEstado,

        @Schema(description = "Nome do estado", example = "São Paulo")
        String nome,

        @Schema(description = "Sigla do estado", example = "SP")
        String sigla

) {

    public static EstadoResponse fromEntity(Estado estado) {
        return new EstadoResponse(
                estado.getIdEstado(),
                estado.getNome(),
                estado.getSigla()
        );
    }
}