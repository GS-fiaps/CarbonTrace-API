package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Alerta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com alerta ambiental")
public record AlertaResponse(

        @Schema(description = "Identificador único do alerta", example = "1")
        Long idAlerta,

        @Schema(description = "Data de emissão do alerta", example = "2026-05-28")
        LocalDate dataEmissao,

        @Schema(description = "Nível de criticidade do alerta", example = "ALTO")
        NivelCriticidade nivelCriticidade,

        @Schema(description = "Descrição do alerta", example = "Alerta emitido por aumento significativo de área desmatada.")
        String descricao,

        @Schema(description = "ID da análise vinculada", example = "1")
        Long analiseId,

        @Schema(description = "Data da análise vinculada", example = "2026-05-22")
        LocalDate dataAnalise,

        @Schema(description = "Área desmatada identificada na análise", example = "8.7")
        Double areaDesmatadaKm2,

        @Schema(description = "Percentual de variação identificado", example = "12.5")
        Double percentualVariacao,

        @Schema(description = "Status da análise vinculada", example = "ATENCAO")
        StatusAlerta statusAlerta

) {

    public static AlertaResponse fromEntity(Alerta alerta) {
        return new AlertaResponse(
                alerta.getIdAlerta(),
                alerta.getDataEmissao(),
                alerta.getNivelCriticidade(),
                alerta.getDescricao(),
                alerta.getAnalise().getIdAnalise(),
                alerta.getAnalise().getDataAnalise(),
                alerta.getAnalise().getAreaDesmatadaKm2(),
                alerta.getAnalise().getPercentualVariacao(),
                alerta.getAnalise().getStatusAlerta()
        );
    }
}