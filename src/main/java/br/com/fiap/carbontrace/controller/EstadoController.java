package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.dto.request.EstadoRequest;
import br.com.fiap.carbontrace.dto.response.EstadoResponse;
import br.com.fiap.carbontrace.service.EstadoService;
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
@RequestMapping("/estados")
@RequiredArgsConstructor
@Tag(name = "Estados", description = "Endpoints para gerenciamento dos estados utilizados no monitoramento ambiental")
public class EstadoController {

    private final EstadoService estadoService;

    @PostMapping
    @Operation(summary = "Cadastrar estado", description = "Cadastra um novo estado no sistema CarbonTrace.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Estado cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    public ResponseEntity<EstadoResponse> cadastrar(@RequestBody @Valid EstadoRequest request) {
        EstadoResponse response = estadoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Listar estados", description = "Retorna todos os estados cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estados listados com sucesso")
    })
    public ResponseEntity<List<EstadoResponse>> listarTodos() {
        List<EstadoResponse> estados = estadoService.listarTodos();
        return ResponseEntity.ok(estados);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar estado por ID", description = "Retorna os dados de um estado específico pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    public ResponseEntity<EstadoResponse> buscarPorId(@PathVariable Long id) {
        EstadoResponse response = estadoService.buscarPorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar estado", description = "Atualiza os dados de um estado existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Estado atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    public ResponseEntity<EstadoResponse> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid EstadoRequest request
    ) {
        EstadoResponse response = estadoService.atualizar(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar estado", description = "Remove um estado cadastrado pelo ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Estado deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        estadoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}