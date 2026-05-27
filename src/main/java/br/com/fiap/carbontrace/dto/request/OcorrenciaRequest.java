package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Ocorrencia;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record OcorrenciaRequest(

        @NotNull(message = "A data da ocorrência é obrigatória")
        @PastOrPresent(message = "A data da ocorrência não pode ser futura")
        LocalDate dataOcorrencia,

        @NotBlank(message = "A descrição da ocorrência é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "A área estimada em km² é obrigatória")
        @Positive(message = "A área estimada em km² deve ser maior que zero")
        Double areaEstimadaKm2,

        @NotNull(message = "O ID da região é obrigatório")
        Long regiaoId,

        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId

) {

    public Ocorrencia toEntity(Regiao regiao, Usuario usuario) {
        return Ocorrencia.builder()
                .dataOcorrencia(dataOcorrencia)
                .descricao(descricao)
                .areaEstimadaKm2(areaEstimadaKm2)
                .regiao(regiao)
                .usuario(usuario)
                .build();
    }
}