package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.EstadoRequest;
import br.com.fiap.carbontrace.dto.response.EstadoResponse;
import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.repositories.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    private final EstadoRepository estadoRepository;

    public EstadoResponse cadastrar(EstadoRequest request) {
        Estado estado = request.toEntity();
        Estado estadoSalvo = estadoRepository.save(estado);

        return EstadoResponse.fromEntity(estadoSalvo);
    }

    public List<EstadoResponse> listarTodos() {
        return estadoRepository.findAll()
                .stream()
                .map(EstadoResponse::fromEntity)
                .toList();
    }

    public EstadoResponse buscarPorId(Long id) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        return EstadoResponse.fromEntity(estado);
    }

    public EstadoResponse atualizar(Long id, EstadoRequest request) {
        Estado estado = estadoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        estado.setNome(request.nome());
        estado.setSigla(request.sigla().toUpperCase());

        Estado estadoAtualizado = estadoRepository.save(estado);

        return EstadoResponse.fromEntity(estadoAtualizado);
    }

    public void deletar(Long id) {
        if (!estadoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Estado não encontrado"
            );
        }

        estadoRepository.deleteById(id);
    }
}