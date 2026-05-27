package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.StatusNotificacao;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.AlertaOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgaoId;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record AlertaOrgaoRequest(

        @NotNull(message = "O ID do alerta é obrigatório")
        Long alertaId,

        @NotNull(message = "O ID do órgão ambiental é obrigatório")
        Long orgaoAmbientalId,

        @NotNull(message = "A data da notificação é obrigatória")
        @PastOrPresent(message = "A data da notificação não pode ser futura")
        LocalDate dataNotificacao,

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