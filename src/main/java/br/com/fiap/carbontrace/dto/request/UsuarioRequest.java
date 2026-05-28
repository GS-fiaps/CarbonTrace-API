package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.TipoUsuario;
import br.com.fiap.carbontrace.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(description = "Dados necessários para cadastro ou atualização de usuário")
public record UsuarioRequest(

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        @NotBlank(message = "O nome é obrigatório")
        @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
        String nome,

        @Schema(description = "E-mail do usuário", example = "joao@email.com")
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "E-mail inválido")
        @Size(max = 100, message = "O e-mail deve ter no máximo 100 caracteres")
        String email,

        @Schema(description = "Senha de acesso do usuário", example = "123456")
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 6, max = 100, message = "A senha deve ter entre 6 e 100 caracteres")
        String senha,

        @Schema(description = "Tipo do usuário no sistema", example = "ANALISTA")
        @NotNull(message = "O tipo do usuário é obrigatório")
        TipoUsuario tipoUsuario

) {

    public Usuario toEntity() {
        return Usuario.builder()
                .nome(nome)
                .email(email)
                .senha(senha)
                .tipoUsuario(tipoUsuario)
                .build();
    }
}