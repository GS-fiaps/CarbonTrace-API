package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

@Schema(description = "Dados necessários para cadastro ou atualização de órgão ambiental")
public record OrgaoAmbientalRequest(

        @Schema(description = "Nome do órgão ambiental", example = "IBAMA")
        @NotBlank(message = "O nome do órgão ambiental é obrigatório")
        @Size(max = 150, message = "O nome do órgão ambiental deve ter no máximo 150 caracteres")
        String nome,

        @Schema(description = "Tipo do órgão ambiental", example = "FEDERAL")
        @NotNull(message = "O tipo do órgão é obrigatório")
        TipoOrgao tipo,

        @Schema(description = "E-mail de contato do órgão ambiental", example = "contato@ibama.gov.br")
        @NotBlank(message = "O e-mail de contato é obrigatório")
        @Email(message = "E-mail de contato inválido")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
        String emailContato,

        @Schema(description = "ID do estado ao qual o órgão pertence", example = "1")
        @NotNull(message = "O ID do estado é obrigatório")
        Long estadoId

) {

    public OrgaoAmbiental toEntity(Estado estado) {
        return OrgaoAmbiental.builder()
                .nome(nome)
                .tipo(tipo)
                .emailContato(emailContato)
                .estado(estado)
                .build();
    }
}