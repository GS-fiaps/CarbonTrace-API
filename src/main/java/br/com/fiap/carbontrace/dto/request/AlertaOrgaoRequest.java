package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.StatusNotificacao;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.AlertaOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgaoId;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para vincular um alerta a um órgão ambiental")
public record AlertaOrgaoRequest(

        @Schema(description = "ID do alerta ambiental", example = "1")
        @NotNull(message = "O ID do alerta é obrigatório")
        Long alertaId,

        @Schema(description = "ID do órgão ambiental que receberá o alerta", example = "1")
        @NotNull(message = "O ID do órgão ambiental é obrigatório")
        Long orgaoAmbientalId,

        @Schema(description = "Data em que o órgão foi notificado", example = "2026-05-28")
        @NotNull(message = "A data da notificação é obrigatória")
        @PastOrPresent(message = "A data da notificação não pode ser futura")
        LocalDate dataNotificacao,

        @Schema(description = "Status da notificação enviada ao órgão", example = "ENVIADO")
        @NotNull(message = "O status da notificação é obrigatório")
        StatusNotificacao statusNotificacao

) {

    public AlertaOrgao toEntity(Alerta alerta, OrgaoAmbiental orgaoAmbiental) {
        return AlertaOrgao.builder()
                .id(new AlertaOrgaoId(alerta.getIdAlerta(), orgaoAmbiental.getIdOrgao()))
                .alerta(alerta)
                .orgaoAmbiental(orgaoAmbiental)
                .dataNotificacao(dataNotificacao)
                .statusNotificacao(statusNotificacao)
                .build();
    }
}