package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.assembler.AlertaAssembler;
import br.com.fiap.carbontrace.dto.request.AlertaRequest;
import br.com.fiap.carbontrace.dto.response.AlertaResponse;
import br.com.fiap.carbontrace.service.AlertaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/alertas")
@RequiredArgsConstructor
@Tag(name = "Alertas", description = "Endpoints para emissão e gerenciamento de alertas ambientais")
public class AlertaController {

    private final AlertaService alertaService;
    private final AlertaAssembler alertaAssembler;

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
    public ResponseEntity<EntityModel<AlertaResponse>> cadastrar(@RequestBody @Valid AlertaRequest request) {
        AlertaResponse response = alertaService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(alertaAssembler.toModel(response));
    }

    @GetMapping
    @Operation(
            summary = "Listar alertas",
            description = "Retorna todos os alertas ambientais cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alertas listados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<AlertaResponse>>> listarTodos() {
        List<EntityModel<AlertaResponse>> alertas = alertaService.listarTodos()
                .stream()
                .map(alertaAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<AlertaResponse>> collection = CollectionModel.of(
                alertas,
                linkTo(methodOn(AlertaController.class).listarTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
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
    public ResponseEntity<EntityModel<AlertaResponse>> buscarPorId(@PathVariable Long id) {
        AlertaResponse response = alertaService.buscarPorId(id);
        return ResponseEntity.ok(alertaAssembler.toModel(response));
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
    public ResponseEntity<EntityModel<AlertaResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid AlertaRequest request
    ) {
        AlertaResponse response = alertaService.atualizar(id, request);
        return ResponseEntity.ok(alertaAssembler.toModel(response));
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