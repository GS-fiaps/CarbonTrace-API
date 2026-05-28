package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.OrgaoAmbientalRequest;
import br.com.fiap.carbontrace.dto.response.OrgaoAmbientalResponse;
import br.com.fiap.carbontrace.model.Estado;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import br.com.fiap.carbontrace.repositories.EstadoRepository;
import br.com.fiap.carbontrace.repositories.OrgaoAmbientalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrgaoAmbientalService {

    private final OrgaoAmbientalRepository orgaoAmbientalRepository;
    private final EstadoRepository estadoRepository;

    public OrgaoAmbientalResponse cadastrar(OrgaoAmbientalRequest request) {
        Estado estado = estadoRepository.findById(request.estadoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        OrgaoAmbiental orgaoAmbiental = request.toEntity(estado);
        OrgaoAmbiental orgaoSalvo = orgaoAmbientalRepository.save(orgaoAmbiental);

        return OrgaoAmbientalResponse.fromEntity(orgaoSalvo);
    }

    public List<OrgaoAmbientalResponse> listarTodos() {
        return orgaoAmbientalRepository.findAll()
                .stream()
                .map(OrgaoAmbientalResponse::fromEntity)
                .toList();
    }

    public OrgaoAmbientalResponse buscarPorId(Long id) {
        OrgaoAmbiental orgaoAmbiental = orgaoAmbientalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Órgão ambiental não encontrado"
                ));

        return OrgaoAmbientalResponse.fromEntity(orgaoAmbiental);
    }

    public OrgaoAmbientalResponse atualizar(Long id, OrgaoAmbientalRequest request) {
        OrgaoAmbiental orgaoAmbiental = orgaoAmbientalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Órgão ambiental não encontrado"
                ));

        Estado estado = estadoRepository.findById(request.estadoId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Estado não encontrado"
                ));

        orgaoAmbiental.setNome(request.nome());
        orgaoAmbiental.setTipo(request.tipo());
        orgaoAmbiental.setEmailContato(request.emailContato());
        orgaoAmbiental.setEstado(estado);

        OrgaoAmbiental orgaoAtualizado = orgaoAmbientalRepository.save(orgaoAmbiental);

        return OrgaoAmbientalResponse.fromEntity(orgaoAtualizado);
    }

    public void deletar(Long id) {
        if (!orgaoAmbientalRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Órgão ambiental não encontrado"
            );
        }

        orgaoAmbientalRepository.deleteById(id);
    }
}