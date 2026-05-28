package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.AnaliseRequest;
import br.com.fiap.carbontrace.dto.response.AnaliseResponse;
import br.com.fiap.carbontrace.model.Analise;
import br.com.fiap.carbontrace.model.ImagemSatelital;
import br.com.fiap.carbontrace.repositories.AnaliseRepository;
import br.com.fiap.carbontrace.repositories.ImagemSatelitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnaliseService {

    private final AnaliseRepository analiseRepository;
    private final ImagemSatelitalRepository imagemSatelitalRepository;

    public AnaliseResponse cadastrar(AnaliseRequest request) {
        ImagemSatelital imagemSatelital = imagemSatelitalRepository.findById(request.imagemSatelitalId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Imagem satelital não encontrada"
                ));

        Analise analise = request.toEntity(imagemSatelital);
        Analise analiseSalva = analiseRepository.save(analise);

        return AnaliseResponse.fromEntity(analiseSalva);
    }

    public List<AnaliseResponse> listarTodos() {
        return analiseRepository.findAll()
                .stream()
                .map(AnaliseResponse::fromEntity)
                .toList();
    }

    public AnaliseResponse buscarPorId(Long id) {
        Analise analise = analiseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Análise não encontrada"
                ));

        return AnaliseResponse.fromEntity(analise);
    }

    public AnaliseResponse atualizar(Long id, AnaliseRequest request) {
        Analise analise = analiseRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Análise não encontrada"
                ));

        ImagemSatelital imagemSatelital = imagemSatelitalRepository.findById(request.imagemSatelitalId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Imagem satelital não encontrada"
                ));

        analise.setDataAnalise(request.dataAnalise());
        analise.setAreaDesmatadaKm2(request.areaDesmatadaKm2());
        analise.setPercentualVariacao(request.percentualVariacao());
        analise.setStatusAlerta(request.statusAlerta());
        analise.setImagemSatelital(imagemSatelital);

        Analise analiseAtualizada = analiseRepository.save(analise);

        return AnaliseResponse.fromEntity(analiseAtualizada);
    }

    public void deletar(Long id) {
        if (!analiseRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Análise não encontrada"
            );
        }

        analiseRepository.deleteById(id);
    }
}