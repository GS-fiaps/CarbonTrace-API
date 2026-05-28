package br.com.fiap.carbontrace.service;

import br.com.fiap.carbontrace.dto.request.AlertaOrgaoRequest;
import br.com.fiap.carbontrace.dto.response.AlertaOrgaoResponse;
import br.com.fiap.carbontrace.model.Alerta;
import br.com.fiap.carbontrace.model.AlertaOrgao;
import br.com.fiap.carbontrace.model.AlertaOrgaoId;
import br.com.fiap.carbontrace.model.OrgaoAmbiental;
import br.com.fiap.carbontrace.repositories.AlertaOrgaoRepository;
import br.com.fiap.carbontrace.repositories.AlertaRepository;
import br.com.fiap.carbontrace.repositories.OrgaoAmbientalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlertaOrgaoService {

    private final AlertaOrgaoRepository alertaOrgaoRepository;
    private final AlertaRepository alertaRepository;
    private final OrgaoAmbientalRepository orgaoAmbientalRepository;

    public AlertaOrgaoResponse cadastrar(AlertaOrgaoRequest request) {
        Alerta alerta = alertaRepository.findById(request.alertaId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alerta não encontrado"
                ));

        OrgaoAmbiental orgaoAmbiental = orgaoAmbientalRepository.findById(request.orgaoAmbientalId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Órgão ambiental não encontrado"
                ));

        AlertaOrgaoId id = new AlertaOrgaoId(
                alerta.getIdAlerta(),
                orgaoAmbiental.getIdOrgao()
        );

        if (alertaOrgaoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Este alerta já foi vinculado a este órgão ambiental"
            );
        }

        AlertaOrgao alertaOrgao = request.toEntity(alerta, orgaoAmbiental);
        AlertaOrgao alertaOrgaoSalvo = alertaOrgaoRepository.save(alertaOrgao);

        return AlertaOrgaoResponse.fromEntity(alertaOrgaoSalvo);
    }

    public List<AlertaOrgaoResponse> listarTodos() {
        return alertaOrgaoRepository.findAll()
                .stream()
                .map(AlertaOrgaoResponse::fromEntity)
                .toList();
    }

    public AlertaOrgaoResponse buscarPorId(Long alertaId, Long orgaoAmbientalId) {
        AlertaOrgaoId id = new AlertaOrgaoId(alertaId, orgaoAmbientalId);

        AlertaOrgao alertaOrgao = alertaOrgaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vínculo entre alerta e órgão ambiental não encontrado"
                ));

        return AlertaOrgaoResponse.fromEntity(alertaOrgao);
    }

    public AlertaOrgaoResponse atualizar(
            Long alertaId,
            Long orgaoAmbientalId,
            AlertaOrgaoRequest request
    ) {
        AlertaOrgaoId id = new AlertaOrgaoId(alertaId, orgaoAmbientalId);

        AlertaOrgao alertaOrgao = alertaOrgaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Vínculo entre alerta e órgão ambiental não encontrado"
                ));

        alertaOrgao.setDataNotificacao(request.dataNotificacao());
        alertaOrgao.setStatusNotificacao(request.statusNotificacao());

        AlertaOrgao alertaOrgaoAtualizado = alertaOrgaoRepository.save(alertaOrgao);

        return AlertaOrgaoResponse.fromEntity(alertaOrgaoAtualizado);
    }

    public void deletar(Long alertaId, Long orgaoAmbientalId) {
        AlertaOrgaoId id = new AlertaOrgaoId(alertaId, orgaoAmbientalId);

        if (!alertaOrgaoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Vínculo entre alerta e órgão ambiental não encontrado"
            );
        }

        alertaOrgaoRepository.deleteById(id);
    }
}