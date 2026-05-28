package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.RelatorioRequest;
import br.com.fiap.carbontrace.dto.response.RelatorioResponse;
import br.com.fiap.carbontrace.model.Relatorio;
import br.com.fiap.carbontrace.model.Usuario;
import br.com.fiap.carbontrace.repositories.RelatorioRepository;
import br.com.fiap.carbontrace.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final RelatorioRepository relatorioRepository;
    private final UsuarioRepository usuarioRepository;

    public RelatorioResponse cadastrar(RelatorioRequest request) {
        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        Relatorio relatorio = request.toEntity(usuario);
        Relatorio relatorioSalvo = relatorioRepository.save(relatorio);

        return RelatorioResponse.fromEntity(relatorioSalvo);
    }

    public List<RelatorioResponse> listarTodos() {
        return relatorioRepository.findAll()
                .stream()
                .map(RelatorioResponse::fromEntity)
                .toList();
    }

    public RelatorioResponse buscarPorId(Long id) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Relatório não encontrado"
                ));

        return RelatorioResponse.fromEntity(relatorio);
    }

    public RelatorioResponse atualizar(Long id, RelatorioRequest request) {
        Relatorio relatorio = relatorioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Relatório não encontrado"
                ));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        relatorio.setTitulo(request.titulo());
        relatorio.setPeriodoInicio(request.periodoInicio());
        relatorio.setPeriodoFim(request.periodoFim());
        relatorio.setUsuario(usuario);

        Relatorio relatorioAtualizado = relatorioRepository.save(relatorio);

        return RelatorioResponse.fromEntity(relatorioAtualizado);
    }

    public void deletar(Long id) {
        if (!relatorioRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Relatório não encontrado"
            );
        }

        relatorioRepository.deleteById(id);
    }
}