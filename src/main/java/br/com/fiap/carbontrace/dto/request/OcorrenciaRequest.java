package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Ocorrencia;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro ou atualização de ocorrência ambiental")
public record OcorrenciaRequest(

        @Schema(description = "Data em que a ocorrência foi identificada", example = "2026-05-20")
        @NotNull(message = "A data da ocorrência é obrigatória")
        @PastOrPresent(message = "A data da ocorrência não pode ser futura")
        LocalDate dataOcorrencia,

        @Schema(description = "Descrição da ocorrência ambiental", example = "Área com indícios de desmatamento detectada por análise satelital.")
        @NotBlank(message = "A descrição da ocorrência é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @Schema(description = "Área estimada afetada em quilômetros quadrados", example = "12.5")
        @NotNull(message = "A área estimada em km² é obrigatória")
        @Positive(message = "A área estimada em km² deve ser maior que zero")
        Double areaEstimadaKm2,

        @Schema(description = "ID da região onde a ocorrência foi registrada", example = "1")
        @NotNull(message = "O ID da região é obrigatório")
        Long regiaoId,

        @Schema(description = "ID do usuário responsável pelo registro", example = "1")
        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId

) {

    public Ocorrencia toEntity(Regiao regiao, Usuario usuario) {
        return Ocorrencia.builder()
                .dataOcorrencia(dataOcorrencia)
                .descricao(descricao)
                .areaEstimadaKm2(areaEstimadaKm2)
                .regiao(regiao)
                .usuario(usuario)
                .build();
    }
}