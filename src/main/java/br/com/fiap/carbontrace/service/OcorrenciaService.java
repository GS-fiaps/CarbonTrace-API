package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.OcorrenciaRequest;
import br.com.fiap.carbontrace.dto.response.OcorrenciaResponse;
import br.com.fiap.carbontrace.model.Ocorrencia;
import br.com.fiap.carbontrace.model.Regiao;
import br.com.fiap.carbontrace.model.Usuario;
import br.com.fiap.carbontrace.repositories.OcorrenciaRepository;
import br.com.fiap.carbontrace.repositories.RegiaoRepository;
import br.com.fiap.carbontrace.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OcorrenciaService {

    private final OcorrenciaRepository ocorrenciaRepository;
    private final RegiaoRepository regiaoRepository;
    private final UsuarioRepository usuarioRepository;

    public OcorrenciaResponse cadastrar(OcorrenciaRequest request) {
        Regiao regiao = regiaoRepository.findById(request.regiaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        Ocorrencia ocorrencia = request.toEntity(regiao, usuario);
        Ocorrencia ocorrenciaSalva = ocorrenciaRepository.save(ocorrencia);

        return OcorrenciaResponse.fromEntity(ocorrenciaSalva);
    }

    public List<OcorrenciaResponse> listarTodos() {
        return ocorrenciaRepository.findAll()
                .stream()
                .map(OcorrenciaResponse::fromEntity)
                .toList();
    }

    public OcorrenciaResponse buscarPorId(Long id) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ocorrência não encontrada"
                ));

        return OcorrenciaResponse.fromEntity(ocorrencia);
    }

    public OcorrenciaResponse atualizar(Long id, OcorrenciaRequest request) {
        Ocorrencia ocorrencia = ocorrenciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Ocorrência não encontrada"
                ));

        Regiao regiao = regiaoRepository.findById(request.regiaoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Região não encontrada"
                ));

        Usuario usuario = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        ocorrencia.setDataOcorrencia(request.dataOcorrencia());
        ocorrencia.setDescricao(request.descricao());
        ocorrencia.setAreaEstimadaKm2(request.areaEstimadaKm2());
        ocorrencia.setRegiao(regiao);
        ocorrencia.setUsuario(usuario);

        Ocorrencia ocorrenciaAtualizada = ocorrenciaRepository.save(ocorrencia);

        return OcorrenciaResponse.fromEntity(ocorrenciaAtualizada);
    }

    public void deletar(Long id) {
        if (!ocorrenciaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Ocorrência não encontrada"
            );
        }

        ocorrenciaRepository.deleteById(id);
    }
}