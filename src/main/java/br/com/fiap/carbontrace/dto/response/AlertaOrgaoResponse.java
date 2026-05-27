package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.enums.StatusNotificacao;
import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgao;

import java.time.LocalDate;

public record AlertaOrgaoResponse(

        Long alertaId,
        LocalDate dataEmissaoAlerta,
        NivelCriticidade nivelCriticidade,
        String descricaoAlerta,

        Long orgaoAmbientalId,
        String nomeOrgao,
        TipoOrgao tipoOrgao,
        String emailContato,

        LocalDate dataNotificacao,
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