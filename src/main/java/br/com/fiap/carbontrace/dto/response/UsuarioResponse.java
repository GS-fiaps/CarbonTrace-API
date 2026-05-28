package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.TipoUsuario;
import br.com.fiap.carbontrace.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com usuário")
public record UsuarioResponse(

        @Schema(description = "Identificador único do usuário", example = "1")
        Long idUsuario,

        @Schema(description = "Nome completo do usuário", example = "João Silva")
        String nome,

        @Schema(description = "E-mail do usuário", example = "joao@email.com")
        String email,

        @Schema(description = "Tipo do usuário", example = "ANALISTA")
        TipoUsuario tipoUsuario,

        @Schema(description = "Data de cadastro do usuário", example = "2026-05-28")
        LocalDate dataCadastro
) {

    public static UsuarioResponse fromEntity(Usuario usuario) {
        return new UsuarioResponse(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario(),
                usuario.getDataCadastro()
        );
    }
}