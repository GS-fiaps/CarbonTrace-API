package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dados retornados após operações com órgão ambiental")
public record OrgaoAmbientalResponse(

        @Schema(description = "Identificador único do órgão ambiental", example = "1")
        Long idOrgao,

        @Schema(description = "Nome do órgão ambiental", example = "IBAMA")
        String nome,

        @Schema(description = "Tipo do órgão ambiental", example = "FEDERAL")
        TipoOrgao tipo,

        @Schema(description = "E-mail de contato", example = "contato@ibama.gov.br")
        String emailContato,

        @Schema(description = "ID do estado vinculado", example = "1")
        Long estadoId,

        @Schema(description = "Nome do estado vinculado", example = "São Paulo")
        String estadoNome,

        @Schema(description = "Sigla do estado vinculado", example = "SP")
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