package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.SateliteRequest;
import br.com.fiap.carbontrace.dto.response.SateliteResponse;
import br.com.fiap.carbontrace.model.Satelite;
import br.com.fiap.carbontrace.repositories.SateliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SateliteService {

    private final SateliteRepository sateliteRepository;

    public SateliteResponse cadastrar(SateliteRequest request) {
        Satelite satelite = request.toEntity();
        Satelite sateliteSalvo = sateliteRepository.save(satelite);

        return SateliteResponse.fromEntity(sateliteSalvo);
    }

    public List<SateliteResponse> listarTodos() {
        return sateliteRepository.findAll()
                .stream()
                .map(SateliteResponse::fromEntity)
                .toList();
    }

    public SateliteResponse buscarPorId(Long id) {
        Satelite satelite = sateliteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Satélite não encontrado"
                ));

        return SateliteResponse.fromEntity(satelite);
    }

    public SateliteResponse atualizar(Long id, SateliteRequest request) {
        Satelite satelite = sateliteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Satélite não encontrado"
                ));

        satelite.setNome(request.nome());
        satelite.setAgencia(request.agencia());
        satelite.setAltitudeKm(request.altitudeKm());
        satelite.setAnoLancamento(request.anoLancamento());

        Satelite sateliteAtualizado = sateliteRepository.save(satelite);

        return SateliteResponse.fromEntity(sateliteAtualizado);
    }

    public void deletar(Long id) {
        if (!sateliteRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Satélite não encontrado"
            );
        }

        sateliteRepository.deleteById(id);
    }
}