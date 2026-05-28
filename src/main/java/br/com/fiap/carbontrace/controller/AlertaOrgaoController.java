package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AlertaOrgaoRequest;
import br.com.fiap.carbontrace.dto.response.AlertaOrgaoResponse;
import br.com.fiap.carbontrace.service.AlertaOrgaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alertas-orgaos")
@RequiredArgsConstructor
@Tag(name = "Alertas e Órgãos", description = "Endpoints para vincular alertas ambientais aos órgãos responsáveis por recebê-los")
public class AlertaOrgaoController {

    private final AlertaOrgaoService alertaOrgaoService;

    @PostMapping
    @Operation(
            summary = "Cadastrar vínculo entre alerta e órgão",
            description = "Cria um vínculo entre um alerta ambiental e um órgão ambiental, registrando a data e o status da notificação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Vínculo cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Alerta ou órgão ambiental não encontrado"),
            @ApiResponse(responseCode = "409", description = "Este alerta já foi vinculado a este órgão ambiental")
    })
    public ResponseEntity<AlertaOrgaoResponse> cadastrar(@RequestBody @Valid AlertaOrgaoRequest request) {
        AlertaOrgaoResponse response = alertaOrgaoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar vínculos entre alertas e órgãos",
            description = "Retorna todos os vínculos cadastrados entre alertas ambientais e órgãos ambientais."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculos listados com sucesso")
    })
    public ResponseEntity<List<AlertaOrgaoResponse>> listarTodos() {
        List<AlertaOrgaoResponse> response = alertaOrgaoService.listarTodos();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    @Operation(
            summary = "Buscar vínculo por chave composta",
            description = "Busca um vínculo específico entre alerta e órgão ambiental usando o ID do alerta e o ID do órgão ambiental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculo encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vínculo entre alerta e órgão ambiental não encontrado")
    })
    public ResponseEntity<AlertaOrgaoResponse> buscarPorId(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId
    ) {
        AlertaOrgaoResponse response = alertaOrgaoService.buscarPorId(alertaId, orgaoAmbientalId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    @Operation(
            summary = "Atualizar vínculo entre alerta e órgão",
            description = "Atualiza a data e o status da notificação de um vínculo existente entre alerta e órgão ambiental."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Vínculo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Vínculo entre alerta e órgão ambiental não encontrado")
    })
    public ResponseEntity<AlertaOrgaoResponse> atualizar(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId,
            @RequestBody @Valid AlertaOrgaoRequest request
    ) {
        AlertaOrgaoResponse response = alertaOrgaoService.atualizar(alertaId, orgaoAmbientalId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/alerta/{alertaId}/orgao/{orgaoAmbientalId}")
    @Operation(
            summary = "Deletar vínculo entre alerta e órgão",
            description = "Remove um vínculo entre alerta ambiental e órgão ambiental usando a chave composta."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Vínculo deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Vínculo entre alerta e órgão ambiental não encontrado")
    })
    public ResponseEntity<Void> deletar(
            @PathVariable Long alertaId,
            @PathVariable Long orgaoAmbientalId
    ) {
        alertaOrgaoService.deletar(alertaId, orgaoAmbientalId);
        return ResponseEntity.noContent().build();
    }
}