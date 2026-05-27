package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.TipoOrgao;
import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OrgaoAmbientalRequest(

        @NotBlank(message = "O nome do órgão ambiental é obrigatório")
        @Size(max = 150, message = "O nome do órgão ambiental deve ter no máximo 150 caracteres")
        String nome,

        @NotNull(message = "O tipo do órgão é obrigatório")
        TipoOrgao tipo,

        @NotBlank(message = "O e-mail de contato é obrigatório")
        @Email(message = "E-mail de contato inválido")
        @Size(max = 150, message = "O e-mail deve ter no máximo 150 caracteres")
        String emailContato,

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