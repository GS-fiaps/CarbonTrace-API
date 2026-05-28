package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.ImagemSatelitalRequest;
import br.com.fiap.carbontrace.dto.response.ImagemSatelitalResponse;
import br.com.fiap.carbontrace.model.ImagemSatelital;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Satelite;
import br.com.fiap.carbontrace.repositories.ImagemSatelitalRepository;
import br.com.fiap.carbontrace.repositories.RegiaoRepository;
import br.com.fiap.carbontrace.repositories.SateliteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImagemSatelitalService {

    private final ImagemSatelitalRepository imagemSatelitalRepository;
    private final RegiaoRepository regiaoRepository;
    private final SateliteRepository sateliteRepository;

    public ImagemSatelitalResponse cadastrar(ImagemSatelitalRequest request) {
        Regiao regiao = regiaoRepository.findById(request.regiaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        Satelite satelite = sateliteRepository.findById(request.sateliteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Satélite não encontrado"
                ));

        ImagemSatelital imagemSatelital = request.toEntity(regiao, satelite);
        ImagemSatelital imagemSalva = imagemSatelitalRepository.save(imagemSatelital);

        return ImagemSatelitalResponse.fromEntity(imagemSalva);
    }

    public List<ImagemSatelitalResponse> listarTodos() {
        return imagemSatelitalRepository.findAll()
                .stream()
                .map(ImagemSatelitalResponse::fromEntity)
                .toList();
    }

    public ImagemSatelitalResponse buscarPorId(Long id) {
        ImagemSatelital imagemSatelital = imagemSatelitalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Imagem satelital não encontrada"
                ));

        return ImagemSatelitalResponse.fromEntity(imagemSatelital);
    }

    public ImagemSatelitalResponse atualizar(Long id, ImagemSatelitalRequest request) {
        ImagemSatelital imagemSatelital = imagemSatelitalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Imagem satelital não encontrada"
                ));

        Regiao regiao = regiaoRepository.findById(request.regiaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        Satelite satelite = sateliteRepository.findById(request.sateliteId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Satélite não encontrado"
                ));

        imagemSatelital.setDataCaptura(request.dataCaptura());
        imagemSatelital.setResolucaoMetros(request.resolucaoMetros());
        imagemSatelital.setUrlImagem(request.urlImagem());
        imagemSatelital.setRegiao(regiao);
        imagemSatelital.setSatelite(satelite);

        ImagemSatelital imagemAtualizada = imagemSatelitalRepository.save(imagemSatelital);

        return ImagemSatelitalResponse.fromEntity(imagemAtualizada);
    }

    public void deletar(Long id) {
        if (!imagemSatelitalRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Imagem satelital não encontrada"
            );
        }

        imagemSatelitalRepository.deleteById(id);
    }
}