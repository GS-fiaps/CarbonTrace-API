package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.assembler.SateliteAssembler;
import br.com.fiap.carbontrace.dto.request.SateliteRequest;
import br.com.fiap.carbontrace.dto.response.SateliteResponse;
import br.com.fiap.carbontrace.service.SateliteService;
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
@RequestMapping("/satelites")
@RequiredArgsConstructor
@Tag(name = "Satélites", description = "Endpoints para gerenciamento dos satélites utilizados na coleta de imagens ambientais")
public class SateliteController {

    private final SateliteService sateliteService;
    private final SateliteAssembler sateliteAssembler;

    @PostMapping
    @Operation(
            summary = "Cadastrar satélite",
            description = "Cadastra um satélite responsável pela captura de imagens ambientais."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Satélite cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição")
    })
    public ResponseEntity<EntityModel<SateliteResponse>> cadastrar(@RequestBody @Valid SateliteRequest request) {
        SateliteResponse response = sateliteService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(sateliteAssembler.toModel(response));
    }

    @GetMapping
    @Operation(
            summary = "Listar satélites",
            description = "Retorna todos os satélites cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Satélites listados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<SateliteResponse>>> listarTodos() {
        List<EntityModel<SateliteResponse>> satelites = sateliteService.listarTodos()
                .stream()
                .map(sateliteAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<SateliteResponse>> collection = CollectionModel.of(
                satelites,
                linkTo(methodOn(SateliteController.class).listarTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar satélite por ID",
            description = "Retorna os dados de um satélite específico pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Satélite encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Satélite não encontrado")
    })
    public ResponseEntity<EntityModel<SateliteResponse>> buscarPorId(@PathVariable Long id) {
        SateliteResponse response = sateliteService.buscarPorId(id);
        return ResponseEntity.ok(sateliteAssembler.toModel(response));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar satélite",
            description = "Atualiza os dados de um satélite existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Satélite atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Satélite não encontrado")
    })
    public ResponseEntity<EntityModel<SateliteResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid SateliteRequest request
    ) {
        SateliteResponse response = sateliteService.atualizar(id, request);
        return ResponseEntity.ok(sateliteAssembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar satélite",
            description = "Remove um satélite cadastrado pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Satélite deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Satélite não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        sateliteService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}