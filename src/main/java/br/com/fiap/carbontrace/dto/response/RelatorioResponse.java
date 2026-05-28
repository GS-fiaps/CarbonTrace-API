package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Relatorio;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(description = "Dados retornados após operações com relatório ambiental")
public record RelatorioResponse(

        @Schema(description = "Identificador único do relatório", example = "1")
        Long idRelatorio,

        @Schema(description = "Data de geração do relatório", example = "2026-05-28")
        LocalDate dataGeracao,

        @Schema(description = "Título do relatório", example = "Relatório de Monitoramento Ambiental")
        String titulo,

        @Schema(description = "Data inicial do período analisado", example = "2026-05-01")
        LocalDate periodoInicio,

        @Schema(description = "Data final do período analisado", example = "2026-05-28")
        LocalDate periodoFim,

        @Schema(description = "ID do usuário responsável", example = "1")
        Long usuarioId,

        @Schema(description = "Nome do usuário responsável", example = "João Silva")
        String usuarioNome,

        @Schema(description = "E-mail do usuário responsável", example = "joao.silva@email.com")
        String usuarioEmail

) {

    public static RelatorioResponse fromEntity(Relatorio relatorio) {
        return new RelatorioResponse(
                relatorio.getIdRelatorio(),
                relatorio.getDataGeracao(),
                relatorio.getTitulo(),
                relatorio.getPeriodoInicio(),
                relatorio.getPeriodoFim(),
                relatorio.getUsuario().getIdUsuario(),
                relatorio.getUsuario().getNome(),
                relatorio.getUsuario().getEmail()
        );
    }
}