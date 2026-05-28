package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.ImagemSatelital;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Satelite;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro ou atualização de imagem satelital")
public record ImagemSatelitalRequest(

        @Schema(description = "Data em que a imagem foi capturada", example = "2026-05-20")
        @NotNull(message = "A data de captura é obrigatória")
        @PastOrPresent(message = "A data de captura não pode ser futura")
        LocalDate dataCaptura,

        @Schema(description = "Resolução da imagem em metros", example = "10.0")
        @NotNull(message = "A resolução em metros é obrigatória")
        @Positive(message = "A resolução em metros deve ser maior que zero")
        Double resolucaoMetros,

        @Schema(description = "URL de acesso à imagem satelital", example = "https://exemplo.com/imagens/amazonia-2026.jpg")
        @NotBlank(message = "A URL da imagem é obrigatória")
        @Size(max = 500, message = "A URL da imagem deve ter no máximo 500 caracteres")
        String urlImagem,

        @Schema(description = "ID da região monitorada vinculada à imagem", example = "1")
        @NotNull(message = "O ID da região é obrigatório")
        Long regiaoId,

        @Schema(description = "ID do satélite responsável pela captura", example = "1")
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