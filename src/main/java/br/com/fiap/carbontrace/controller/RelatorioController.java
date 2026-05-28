package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.assembler.RelatorioAssembler;
import br.com.fiap.carbontrace.dto.request.RelatorioRequest;
import br.com.fiap.carbontrace.dto.response.RelatorioResponse;
import br.com.fiap.carbontrace.service.RelatorioService;
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
@RequestMapping("/relatorios")
@RequiredArgsConstructor
@Tag(name = "Relatórios", description = "Endpoints para geração e gerenciamento de relatórios ambientais")
public class RelatorioController {

    private final RelatorioService relatorioService;
    private final RelatorioAssembler relatorioAssembler;

    @PostMapping
    @Operation(
            summary = "Cadastrar relatório",
            description = "Cadastra um relatório ambiental vinculado a um usuário responsável."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Relatório cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    public ResponseEntity<EntityModel<RelatorioResponse>> cadastrar(@RequestBody @Valid RelatorioRequest request) {
        RelatorioResponse response = relatorioService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(relatorioAssembler.toModel(response));
    }

    @GetMapping
    @Operation(
            summary = "Listar relatórios",
            description = "Retorna todos os relatórios ambientais cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatórios listados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<RelatorioResponse>>> listarTodos() {
        List<EntityModel<RelatorioResponse>> relatorios = relatorioService.listarTodos()
                .stream()
                .map(relatorioAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<RelatorioResponse>> collection = CollectionModel.of(
                relatorios,
                linkTo(methodOn(RelatorioController.class).listarTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar relatório por ID",
            description = "Retorna os dados de um relatório ambiental específico pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Relatório não encontrado")
    })
    public ResponseEntity<EntityModel<RelatorioResponse>> buscarPorId(@PathVariable Long id) {
        RelatorioResponse response = relatorioService.buscarPorId(id);
        return ResponseEntity.ok(relatorioAssembler.toModel(response));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar relatório",
            description = "Atualiza os dados de um relatório ambiental existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Relatório atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Relatório ou usuário não encontrado")
    })
    public ResponseEntity<EntityModel<RelatorioResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RelatorioRequest request
    ) {
        RelatorioResponse response = relatorioService.atualizar(id, request);
        return ResponseEntity.ok(relatorioAssembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar relatório",
            description = "Remove um relatório ambiental cadastrado pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Relatório deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Relatório não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        relatorioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}