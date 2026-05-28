package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.Analise;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro ou atualização de alerta ambiental")
public record AlertaRequest(

        @Schema(description = "Data em que o alerta foi emitido", example = "2026-05-28")
        @NotNull(message = "A data de emissão é obrigatória")
        @PastOrPresent(message = "A data de emissão não pode ser futura")
        LocalDate dataEmissao,

        @Schema(description = "Nível de criticidade do alerta", example = "ALTO")
        @NotNull(message = "O nível de criticidade é obrigatório")
        NivelCriticidade nivelCriticidade,

        @Schema(description = "Descrição do alerta ambiental", example = "Alerta emitido por aumento significativo de área desmatada.")
        @NotBlank(message = "A descrição do alerta é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @Schema(description = "ID da análise vinculada ao alerta", example = "1")
        @NotNull(message = "O ID da análise é obrigatório")
        Long analiseId

) {

    public Alerta toEntity(Analise analise) {
        return Alerta.builder()
                .dataEmissao(dataEmissao)
                .nivelCriticidade(nivelCriticidade)
                .descricao(descricao)
                .analise(analise)
                .build();
    }
}