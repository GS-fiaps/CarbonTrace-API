package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.TipoUsuario;
import br.com.fiap.carbontrace.model.Usuario;

import java.time.LocalDate;

public record UsuarioResponse(
        Long idUsuario,
        String nome,
        String email,
        TipoUsuario tipoUsuario,
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