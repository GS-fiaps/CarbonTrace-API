package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Analise;
import br.com.fiap.carbontrace.model.ImagemSatelital;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.LocalDate;

public record AnaliseRequest(

        @NotNull(message = "A data da análise é obrigatória")
        @PastOrPresent(message = "A data da análise não pode ser futura")
        LocalDate dataAnalise,

        @NotNull(message = "A área desmatada em km² é obrigatória")
        @PositiveOrZero(message = "A área desmatada deve ser zero ou maior")
        Double areaDesmatadaKm2,

        @NotNull(message = "O percentual de variação é obrigatório")
        Double percentualVariacao,

        @NotNull(message = "O status do alerta é obrigatório")
        StatusAlerta statusAlerta,

        @NotNull(message = "O ID da imagem satelital é obrigatório")
        Long imagemSatelitalId

) {

    public Analise toEntity(ImagemSatelital imagemSatelital) {
        return Analise.builder()
                .dataAnalise(dataAnalise)
                .areaDesmatadaKm2(areaDesmatadaKm2)
                .percentualVariacao(percentualVariacao)
                .statusAlerta(statusAlerta)
                .imagemSatelital(imagemSatelital)
                .build();
    }
}