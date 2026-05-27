package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Estado;

public record EstadoResponse(
        Long idEstado,
        String nome,
        String sigla
) {

    public static EstadoResponse fromEntity(Estado estado) {
        return new EstadoResponse(
                estado.getIdEstado(),
                estado.getNome(),
                estado.getSigla()
        );
    }
}