package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;

public record OrgaoAmbientalResponse(
        Long idOrgao,
        String nome,
        TipoOrgao tipo,
        String emailContato,

        Long estadoId,
        String estadoNome,
        String estadoSigla
) {

    public static OrgaoAmbientalResponse fromEntity(OrgaoAmbiental orgaoAmbiental) {
        return new OrgaoAmbientalResponse(
                orgaoAmbiental.getIdOrgao(),
                orgaoAmbiental.getNome(),
                orgaoAmbiental.getTipo(),
                orgaoAmbiental.getEmailContato(),

                orgaoAmbiental.getEstado().getIdEstado(),
                orgaoAmbiental.getEstado().getNome(),
                orgaoAmbiental.getEstado().getSigla()
        );
    }
}