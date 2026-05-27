package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.enums.NivelCriticidade;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.Analise;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlertaRequest(

        @NotNull(message = "A data de emissão é obrigatória")
        @PastOrPresent(message = "A data de emissão não pode ser futura")
        LocalDate dataEmissao,

        @NotNull(message = "O nível de criticidade é obrigatório")
        NivelCriticidade nivelCriticidade,

        @NotBlank(message = "A descrição do alerta é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "O ID da análise é obrigatório")
        Long analiseId

) {

    public Alerta toEntity(Analise analise) {
        return Alerta.builder()
                .dataEmissao(dataEmissao)
                .nivelCriticidade(nivelCriticidade)
                .descricao(descricao)
                .analise(analise)
                .build();
    }
}