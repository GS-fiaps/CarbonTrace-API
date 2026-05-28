package br.com.fiap.carbontrace.dto.request;

import br.com.fiap.carbontrace.model.Relatorio;
import br.com.fiap.carbontrace.model.Usuario;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "Dados necessários para cadastro ou atualização de relatório ambiental")
public record RelatorioRequest(

        @Schema(description = "Data de geração do relatório", example = "2026-05-28")
        @NotNull(message = "A data de geração é obrigatória")
        LocalDate dataGeracao,

        @Schema(description = "Título do relatório", example = "Relatório de Monitoramento Ambiental")
        @NotBlank(message = "O título do relatório é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @Schema(description = "Data inicial do período analisado", example = "2026-05-01")
        @NotNull(message = "A data de início do período é obrigatória")
        LocalDate periodoInicio,

        @Schema(description = "Data final do período analisado", example = "2026-05-28")
        @NotNull(message = "A data de fim do período é obrigatória")
        LocalDate periodoFim,

        @Schema(description = "ID do usuário responsável pelo relatório", example = "1")
        @NotNull(message = "O ID do usuário é obrigatório")
        Long usuarioId

) {

    public Relatorio toEntity(Usuario usuario) {
        return Relatorio.builder()
                .dataGeracao(dataGeracao)
                .titulo(titulo)
                .periodoInicio(periodoInicio)
                .periodoFim(periodoFim)
                .usuario(usuario)
                .build();
    }
}