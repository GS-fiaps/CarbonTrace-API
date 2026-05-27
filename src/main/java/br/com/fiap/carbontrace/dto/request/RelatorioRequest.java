package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Relatorio;
import br.com.fiap.carbontrace.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RelatorioRequest(

        @NotBlank(message = "O título do relatório é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @NotNull(message = "A data de início do período é obrigatória")
        LocalDate periodoInicio,

        @NotNull(message = "A data de fim do período é obrigatória")
        LocalDate periodoFim,

        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId

) {

    public Relatorio toEntity(Usuario usuario) {
        return Relatorio.builder()
                .titulo(titulo)
                .periodoInicio(periodoInicio)
                .periodoFim(periodoFim)
                .usuario(usuario)
                .build();
    }
}