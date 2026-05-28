package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.RegiaoRequest;
import br.com.fiap.carbontrace.dto.response.RegiaoResponse;
import br.com.fiap.carbontrace.service.RegiaoService;
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
@RequestMapping("/regioes")
@RequiredArgsConstructor
@Tag(name = "Regiões", description = "Endpoints para gerenciamento de regiões ambientais monitoradas")
public class RegiaoController {

    private final RegiaoService regiaoService;

    @PostMapping
    @Operation(
            summary = "Cadastrar região",
            description = "Cadastra uma nova região ambiental vinculada a um estado existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Região cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    public ResponseEntity<RegiaoResponse> cadastrar(@RequestBody @Valid RegiaoRequest request) {
        RegiaoResponse response = regiaoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar regiões",
            description = "Retorna todas as regiões ambientais cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Regiões listadas com sucesso")
    })
    public ResponseEntity<List<RegiaoResponse>> listarTodos() {
        List<RegiaoResponse> regioes = regiaoService.listarTodos();
        return ResponseEntity.ok(regioes);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar região por ID",
            description = "Retorna os dados de uma região ambiental específica pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Região encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Região não encontrada")
    })
    public ResponseEntity<RegiaoResponse> buscarPorId(@PathVariable Long id) {
        RegiaoResponse response = regiaoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar região",
            description = "Atualiza os dados de uma região ambiental existente e permite alterar o estado vinculado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Região atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Região ou estado não encontrado")
    })
    public ResponseEntity<RegiaoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RegiaoRequest request
    ) {
        RegiaoResponse response = regiaoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar região",
            description = "Remove uma região ambiental cadastrada pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Região deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Região não encontrada")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        regiaoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}