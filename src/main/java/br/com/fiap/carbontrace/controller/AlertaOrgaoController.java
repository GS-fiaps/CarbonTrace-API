package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AlertaOrgaoRequest;
import br.com.fiap.carbontrace.dto.response.AlertaOrgaoResponse;
import br.com.fiap.carbontrace.service.AlertaOrgaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas-orgaos")
@RequiredArgsConstructor
public class AlertaOrgaoController {

    private final AlertaOrgaoService alertaOrgaoService;

    @PostMapping
    public ResponseEntity<AlertaOrgaoResponse> cadastrar(@RequestBody @Valid AlertaOrgaoRequest request) {
        AlertaOrgaoResponse response = alertaOrgaoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<AlertaOrgaoResponse>> listarTodos() {
        List<AlertaOrgaoResponse> response = alertaOrgaoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    public ResponseEntity<AlertaOrgaoResponse> buscarPorId(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId
    ) {
        AlertaOrgaoResponse response = alertaOrgaoService.buscarPorId(alertaId, orgaoAmbientalId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    public ResponseEntity<AlertaOrgaoResponse> atualizar(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId,
            @RequestBody @Valid AlertaOrgaoRequest request
    ) {
        AlertaOrgaoResponse response = alertaOrgaoService.atualizar(alertaId, orgaoAmbientalId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    public ResponseEntity<Void> deletar(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId
    ) {
        alertaOrgaoService.deletar(alertaId, orgaoAmbientalId);
        return ResponseEntity.noContent().build();
    }
}