package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.ImagemSatelital;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Satelite;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ImagemSatelitalRequest(

        @NotNull(message = "A data de captura é obrigatória")
        @PastOrPresent(message = "A data de captura não pode ser futura")
        LocalDate dataCaptura,

        @NotNull(message = "A resolução em metros é obrigatória")
        @Positive(message = "A resolução em metros deve ser maior que zero")
        Double resolucaoMetros,

        @NotBlank(message = "A URL da imagem é obrigatória")
        @Size(max = 500, message = "A URL da imagem deve ter no máximo 500 caracteres")
        String urlImagem,

        @NotNull(message = "O ID da região é obrigatório")
        Long regiaoId,

        @NotNull(message = "O ID do satélite é obrigatório")
        Long sateliteId

) {

    public ImagemSatelital toEntity(Regiao regiao, Satelite satelite) {
        return ImagemSatelital.builder()
                .dataCaptura(dataCaptura)
                .resolucaoMetros(resolucaoMetros)
                .urlImagem(urlImagem)
                .regiao(regiao)
                .satelite(satelite)
                .build();
    }
}