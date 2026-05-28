package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.OcorrenciaRequest;
import br.com.fiap.carbontrace.dto.response.OcorrenciaResponse;
import br.com.fiap.carbontrace.service.OcorrenciaService;
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
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
@Tag(name = "Ocorrências", description = "Endpoints para registro e gerenciamento de ocorrências ambientais")
public class OcorrenciaController {

    private final OcorrenciaService ocorrenciaService;

    @PostMapping
    @Operation(
            summary = "Cadastrar ocorrência",
            description = "Registra uma ocorrência ambiental vinculada a uma região monitorada e a um usuário responsável."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ocorrência cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Região ou usuário não encontrado")
    })
    public ResponseEntity<OcorrenciaResponse> cadastrar(@RequestBody @Valid OcorrenciaRequest request) {
        OcorrenciaResponse response = ocorrenciaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar ocorrências",
            description = "Retorna todas as ocorrências ambientais cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocorrências listadas com sucesso")
    })
    public ResponseEntity<List<OcorrenciaResponse>> listarTodos() {
        List<OcorrenciaResponse> ocorrencias = ocorrenciaService.listarTodos();
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar ocorrência por ID",
            description = "Retorna os dados de uma ocorrência ambiental específica pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocorrência encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ocorrência não encontrada")
    })
    public ResponseEntity<OcorrenciaResponse> buscarPorId(@PathVariable Long id) {
        OcorrenciaResponse response = ocorrenciaService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar ocorrência",
            description = "Atualiza os dados de uma ocorrência ambiental existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ocorrência atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Ocorrência, região ou usuário não encontrado")
    })
    public ResponseEntity<OcorrenciaResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid OcorrenciaRequest request
    ) {
        OcorrenciaResponse response = ocorrenciaService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar ocorrência",
            description = "Remove uma ocorrência ambiental cadastrada pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ocorrência deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ocorrência não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        ocorrenciaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}