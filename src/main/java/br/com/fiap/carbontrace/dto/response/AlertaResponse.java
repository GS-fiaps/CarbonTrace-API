package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Alerta;

import java.time.LocalDate;

public record AlertaResponse(
        Long idAlerta,
        LocalDate dataEmissao,
        NivelCriticidade nivelCriticidade,
        String descricao,

        Long analiseId,
        LocalDate dataAnalise,
        Double areaDesmatadaKm2,
        Double percentualVariacao,
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