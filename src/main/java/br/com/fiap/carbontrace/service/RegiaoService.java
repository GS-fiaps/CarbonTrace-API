package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.RegiaoRequest;
import br.com.fiap.carbontrace.dto.response.RegiaoResponse;
import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.repositories.EstadoRepository;
import br.com.fiap.carbontrace.repositories.RegiaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegiaoService {

    private final RegiaoRepository regiaoRepository;
    private final EstadoRepository estadoRepository;

    public RegiaoResponse cadastrar(RegiaoRequest request) {
        Estado estado = estadoRepository.findById(request.estadoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        Regiao regiao = request.toEntity(estado);
        Regiao regiaoSalva = regiaoRepository.save(regiao);

        return RegiaoResponse.fromEntity(regiaoSalva);
    }

    public List<RegiaoResponse> listarTodos() {
        return regiaoRepository.findAll()
                .stream()
                .map(RegiaoResponse::fromEntity)
                .toList();
    }

    public RegiaoResponse buscarPorId(Long id) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        return RegiaoResponse.fromEntity(regiao);
    }

    public RegiaoResponse atualizar(Long id, RegiaoRequest request) {
        Regiao regiao = regiaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        Estado estado = estadoRepository.findById(request.estadoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        regiao.setNome(request.nome());
        regiao.setLatitude(request.latitude());
        regiao.setAreaKm2(request.areaKm2());
        regiao.setEstado(estado);

        Regiao regiaoAtualizada = regiaoRepository.save(regiao);

        return RegiaoResponse.fromEntity(regiaoAtualizada);
    }

    public void deletar(Long id) {
        if (!regiaoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Região não encontrada"
            );
        }

        regiaoRepository.deleteById(id);
    }
}