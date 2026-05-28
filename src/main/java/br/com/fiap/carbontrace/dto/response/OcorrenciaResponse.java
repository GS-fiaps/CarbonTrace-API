package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Ocorrencia;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com ocorrência ambiental")
public record OcorrenciaResponse(

        @Schema(description = "Identificador único da ocorrência", example = "1")
        Long idOcorrencia,

        @Schema(description = "Data da ocorrência", example = "2026-05-20")
        LocalDate dataOcorrencia,

        @Schema(description = "Descrição da ocorrência", example = "Área com indícios de desmatamento detectada por análise satelital.")
        String descricao,

        @Schema(description = "Área estimada afetada em quilômetros quadrados", example = "12.5")
        Double areaEstimadaKm2,

        @Schema(description = "ID da região vinculada", example = "1")
        Long regiaoId,

        @Schema(description = "Nome da região vinculada", example = "Amazônia Legal")
        String regiaoNome,

        @Schema(description = "ID do usuário responsável", example = "1")
        Long usuarioId,

        @Schema(description = "Nome do usuário responsável", example = "João Silva")
        String usuarioNome,

        @Schema(description = "E-mail do usuário responsável", example = "joao.silva@email.com")
        String usuarioEmail

) {

    public static OcorrenciaResponse fromEntity(Ocorrencia ocorrencia) {
        return new OcorrenciaResponse(
                ocorrencia.getIdOcorrencia(),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getDescricao(),
                ocorrencia.getAreaEstimadaKm2(),
                ocorrencia.getRegiao().getIdRegiao(),
                ocorrencia.getRegiao().getNome(),
                ocorrencia.getUsuario().getIdUsuario(),
                ocorrencia.getUsuario().getNome(),
                ocorrencia.getUsuario().getEmail()
        );
    }
}