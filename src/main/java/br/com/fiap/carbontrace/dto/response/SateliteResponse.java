package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Satelite;

public record SateliteResponse(
        Long idSatelite,
        String nome,
        String agencia,
        Double altitudeKm,
        Integer anoLancamento
) {

    public static SateliteResponse fromEntity(Satelite satelite) {
        return new SateliteResponse(
                satelite.getIdSatelite(),
                satelite.getNome(),
                satelite.getAgencia(),
                satelite.getAltitudeKm(),
                satelite.getAnoLancamento()
        );
    }
}