package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.enums.StatusNotificacao;
import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgao;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após vincular um alerta a um órgão ambiental")
public record AlertaOrgaoResponse(

        @Schema(description = "ID do alerta vinculado", example = "1")
        Long alertaId,

        @Schema(description = "Data de emissão do alerta", example = "2026-05-28")
        LocalDate dataEmissaoAlerta,

        @Schema(description = "Nível de criticidade do alerta", example = "ALTO")
        NivelCriticidade nivelCriticidade,

        @Schema(description = "Descrição do alerta", example = "Alerta emitido por aumento significativo de área desmatada.")
        String descricaoAlerta,

        @Schema(description = "ID do órgão ambiental vinculado", example = "1")
        Long orgaoAmbientalId,

        @Schema(description = "Nome do órgão ambiental", example = "IBAMA")
        String nomeOrgao,

        @Schema(description = "Tipo do órgão ambiental", example = "FEDERAL")
        TipoOrgao tipoOrgao,

        @Schema(description = "E-mail de contato do órgão ambiental", example = "contato@ibama.gov.br")
        String emailContato,

        @Schema(description = "Data da notificação enviada ao órgão", example = "2026-05-28")
        LocalDate dataNotificacao,

        @Schema(description = "Status da notificação", example = "ENVIADO")
        StatusNotificacao statusNotificacao

) {

    public static AlertaOrgaoResponse fromEntity(AlertaOrgao alertaOrgao) {
        return new AlertaOrgaoResponse(
                alertaOrgao.getAlerta().getIdAlerta(),
                alertaOrgao.getAlerta().getDataEmissao(),
                alertaOrgao.getAlerta().getNivelCriticidade(),
                alertaOrgao.getAlerta().getDescricao(),
                alertaOrgao.getOrgaoAmbiental().getIdOrgao(),
                alertaOrgao.getOrgaoAmbiental().getNome(),
                alertaOrgao.getOrgaoAmbiental().getTipo(),
                alertaOrgao.getOrgaoAmbiental().getEmailContato(),
                alertaOrgao.getDataNotificacao(),
                alertaOrgao.getStatusNotificacao()
        );
    }
}