package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AlertaRequest;
import br.com.fiap.carbontrace.dto.response.AlertaResponse;
import br.com.fiap.carbontrace.service.AlertaService;
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
@RequestMapping("/alertas")
@RequiredArgsConstructor
@Tag(name = "Alertas", description = "Endpoints para emissão e gerenciamento de alertas ambientais")
public class AlertaController {

    private final AlertaService alertaService;

    @PostMapping
    @Operation(
            summary = "Cadastrar alerta",
            description = "Emite um alerta ambiental vinculado a uma análise existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alerta cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Análise não encontrada")
    })
    public ResponseEntity<AlertaResponse> cadastrar(@RequestBody @Valid AlertaRequest request) {
        AlertaResponse response = alertaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar alertas",
            description = "Retorna todos os alertas ambientais cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alertas listados com sucesso")
    })
    public ResponseEntity<List<AlertaResponse>> listarTodos() {
        List<AlertaResponse> alertas = alertaService.listarTodos();
        return ResponseEntity.ok(alertas);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar alerta por ID",
            description = "Retorna os dados de um alerta ambiental específico pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
    })
    public ResponseEntity<AlertaResponse> buscarPorId(@PathVariable Long id) {
        AlertaResponse response = alertaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar alerta",
            description = "Atualiza os dados de um alerta ambiental existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerta atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Alerta ou análise não encontrado")
    })
    public ResponseEntity<AlertaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlertaRequest request
    ) {
        AlertaResponse response = alertaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar alerta",
            description = "Remove um alerta ambiental cadastrado pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alerta deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Alerta não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alertaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}