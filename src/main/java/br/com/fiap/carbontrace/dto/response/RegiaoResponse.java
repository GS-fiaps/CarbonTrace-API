package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Regiao;

public record RegiaoResponse(
        Long idRegiao,
        String nome,
        Double latitude,
        Double areaKm2,
        Long estadoId,
        String estadoNome,
        String estadoSigla
) {

    public static RegiaoResponse fromEntity(Regiao regiao) {
        return new RegiaoResponse(
                regiao.getIdRegiao(),
                regiao.getNome(),
                regiao.getLatitude(),
                regiao.getAreaKm2(),
                regiao.getEstado().getIdEstado(),
                regiao.getEstado().getNome(),
                regiao.getEstado().getSigla()
        );
    }
}