package br.com.fiap.carbontrace.dto.response;

import br.com.fiap.carbontrace.model.Relatorio;

import java.time.LocalDate;

public record RelatorioResponse(
        Long idRelatorio,
        LocalDate dataGeracao,
        String titulo,
        LocalDate periodoInicio,
        LocalDate periodoFim,

        Long usuarioId,
        String usuarioNome,
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