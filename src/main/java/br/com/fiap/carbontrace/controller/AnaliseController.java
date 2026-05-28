package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.AnaliseRequest;
import br.com.fiap.carbontrace.dto.response.AnaliseResponse;
import br.com.fiap.carbontrace.service.AnaliseService;
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
@RequestMapping("/analises")
@RequiredArgsConstructor
@Tag(name = "Análises", description = "Endpoints para análises de imagens satelitais e identificação de áreas desmatadas")
public class AnaliseController {

    private final AnaliseService analiseService;

    @PostMapping
    @Operation(
            summary = "Cadastrar análise",
            description = "Cadastra uma análise ambiental a partir de uma imagem satelital existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Análise cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Imagem satelital não encontrada")
    })
    public ResponseEntity<AnaliseResponse> cadastrar(@RequestBody @Valid AnaliseRequest request) {
        AnaliseResponse response = analiseService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar análises",
            description = "Retorna todas as análises ambientais cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Análises listadas com sucesso")
    })
    public ResponseEntity<List<AnaliseResponse>> listarTodos() {
        List<AnaliseResponse> analises = analiseService.listarTodos();
        return ResponseEntity.ok(analises);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar análise por ID",
            description = "Retorna os dados de uma análise ambiental específica pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Análise encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Análise não encontrada")
    })
    public ResponseEntity<AnaliseResponse> buscarPorId(@PathVariable Long id) {
        AnaliseResponse response = analiseService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar análise",
            description = "Atualiza os dados de uma análise ambiental existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Análise atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Análise ou imagem satelital não encontrada")
    })
    public ResponseEntity<AnaliseResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AnaliseRequest request
    ) {
        AnaliseResponse response = analiseService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar análise",
            description = "Remove uma análise ambiental cadastrada pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Análise deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Análise não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        analiseService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}