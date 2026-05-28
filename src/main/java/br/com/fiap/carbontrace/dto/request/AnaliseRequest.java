package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.StatusAlerta;
import br.com.fiap.carbontrace.model.Analise;
import br.com.fiap.carbontrace.model.ImagemSatelital;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro ou atualização de análise ambiental")
public record AnaliseRequest(

        @Schema(description = "Data em que a análise foi realizada", example = "2026-05-22")
        @NotNull(message = "A data da análise é obrigatória")
        @PastOrPresent(message = "A data da análise não pode ser futura")
        LocalDate dataAnalise,

        @Schema(description = "Área desmatada identificada em quilômetros quadrados", example = "8.7")
        @NotNull(message = "A área desmatada em km² é obrigatória")
        @PositiveOrZero(message = "A área desmatada deve ser zero ou maior")
        Double areaDesmatadaKm2,

        @Schema(description = "Percentual de variação em relação ao monitoramento anterior", example = "12.5")
        @NotNull(message = "O percentual de variação é obrigatório")
        Double percentualVariacao,

        @Schema(description = "Status da análise ambiental", example = "ATENCAO")
        @NotNull(message = "O status do alerta é obrigatório")
        StatusAlerta statusAlerta,

        @Schema(description = "ID da imagem satelital utilizada na análise", example = "1")
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