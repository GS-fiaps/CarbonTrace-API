package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.AlertaRequest;
import br.com.fiap.carbontrace.dto.response.AlertaResponse;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.Analise;
import br.com.fiap.carbontrace.repositories.AlertaRepository;
import br.com.fiap.carbontrace.repositories.AnaliseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaService {

    private final AlertaRepository alertaRepository;
    private final AnaliseRepository analiseRepository;

    public AlertaResponse cadastrar(AlertaRequest request) {
        Analise analise = analiseRepository.findById(request.analiseId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Análise não encontrada"
                ));

        Alerta alerta = request.toEntity(analise);
        Alerta alertaSalvo = alertaRepository.save(alerta);

        return AlertaResponse.fromEntity(alertaSalvo);
    }

    public List<AlertaResponse> listarTodos() {
        return alertaRepository.findAll()
                .stream()
                .map(AlertaResponse::fromEntity)
                .toList();
    }

    public AlertaResponse buscarPorId(Long id) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alerta não encontrado"
                ));

        return AlertaResponse.fromEntity(alerta);
    }

    public AlertaResponse atualizar(Long id, AlertaRequest request) {
        Alerta alerta = alertaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alerta não encontrado"
                ));

        Analise analise = analiseRepository.findById(request.analiseId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Análise não encontrada"
                ));

        alerta.setDataEmissao(request.dataEmissao());
        alerta.setNivelCriticidade(request.nivelCriticidade());
        alerta.setDescricao(request.descricao());
        alerta.setAnalise(analise);

        Alerta alertaAtualizado = alertaRepository.save(alerta);

        return AlertaResponse.fromEntity(alertaAtualizado);
    }

    public void deletar(Long id) {
        if (!alertaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Alerta não encontrado"
            );
        }

        alertaRepository.deleteById(id);
    }
}