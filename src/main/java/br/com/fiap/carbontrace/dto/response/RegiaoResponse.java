package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Regiao;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados após operações com região ambiental")
public record RegiaoResponse(

        @Schema(description = "Identificador único da região", example = "1")
        Long idRegiao,

        @Schema(description = "Nome da região monitorada", example = "Amazônia Legal")
        String nome,

        @Schema(description = "Latitude da região", example = "-3.4653")
        Double latitude,

        @Schema(description = "Longitude da região", example = "-62.2159")
        Double longitude,

        @Schema(description = "Área da região em quilômetros quadrados", example = "5000000.0")
        Double areaKm2,

        @Schema(description = "ID do estado vinculado", example = "1")
        Long estadoId,

        @Schema(description = "Nome do estado vinculado", example = "Amazonas")
        String estadoNome,

        @Schema(description = "Sigla do estado vinculado", example = "AM")
        String estadoSigla

) {

    public static RegiaoResponse fromEntity(Regiao regiao) {
        return new RegiaoResponse(
                regiao.getIdRegiao(),
                regiao.getNome(),
                regiao.getLatitude(),
                regiao.getLongitude(),
                regiao.getAreaKm2(),
                regiao.getEstado().getIdEstado(),
                regiao.getEstado().getNome(),
                regiao.getEstado().getSigla()
        );
    }
}