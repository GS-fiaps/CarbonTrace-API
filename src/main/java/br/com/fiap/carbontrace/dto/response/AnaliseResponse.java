package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Analise;

import java.time.LocalDate;

public record AnaliseResponse(
        Long idAnalise,
        LocalDate dataAnalise,
        Double areaDesmatadaKm2,
        Double percentualVariacao,
        StatusAlerta statusAlerta,

        Long imagemSatelitalId,
        LocalDate dataCapturaImagem,
        String urlImagem,

        Long regiaoId,
        String regiaoNome,

        Long sateliteId,
        String sateliteNome
) {

    public static AnaliseResponse fromEntity(Analise analise) {
        return new AnaliseResponse(
                analise.getIdAnalise(),
                analise.getDataAnalise(),
                analise.getAreaDesmatadaKm2(),
                analise.getPercentualVariacao(),
                analise.getStatusAlerta(),

                analise.getImagemSatelital().getIdImagem(),
                analise.getImagemSatelital().getDataCaptura(),
                analise.getImagemSatelital().getUrlImagem(),

                analise.getImagemSatelital().getRegiao().getIdRegiao(),
                analise.getImagemSatelital().getRegiao().getNome(),

                analise.getImagemSatelital().getSatelite().getIdSatelite(),
                analise.getImagemSatelital().getSatelite().getNome()
        );
    }
}