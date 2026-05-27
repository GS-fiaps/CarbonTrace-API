package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Ocorrencia;

import java.time.LocalDate;

public record OcorrenciaResponse(
        Long idOcorrencia,
        LocalDate dataOcorrencia,
        String descricao,
        Double areaEstimadaKm2,

        Long regiaoId,
        String regiaoNome,

        Long usuarioId,
        String usuarioNome,
        String usuarioEmail
) {

    public static OcorrenciaResponse fromEntity(Ocorrencia ocorrencia) {
        return new OcorrenciaResponse(
                ocorrencia.getIdOcorrencia(),
                ocorrencia.getDataOcorrencia(),
                ocorrencia.getDescricao(),
                ocorrencia.getAreaEstimadaKm2(),

                ocorrencia.getRegiao().getIdRegiao(),
                ocorrencia.getRegiao().getNome(),

                ocorrencia.getUsuario().getIdUsuario(),
                ocorrencia.getUsuario().getNome(),
                ocorrencia.getUsuario().getEmail()
        );
    }
}