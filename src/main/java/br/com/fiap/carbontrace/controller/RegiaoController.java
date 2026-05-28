package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.assembler.RegiaoAssembler;
import br.com.fiap.carbontrace.dto.request.RegiaoRequest;
import br.com.fiap.carbontrace.dto.response.RegiaoResponse;
import br.com.fiap.carbontrace.service.RegiaoService;
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
@RequestMapping("/regioes")
@RequiredArgsConstructor
@Tag(name = "Regiões", description = "Endpoints para gerenciamento de regiões ambientais monitoradas")
public class RegiaoController {

    private final RegiaoService regiaoService;
    private final RegiaoAssembler regiaoAssembler;

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
    public ResponseEntity<EntityModel<RegiaoResponse>> cadastrar(@RequestBody @Valid RegiaoRequest request) {
        RegiaoResponse response = regiaoService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(regiaoAssembler.toModel(response));
    }

    @GetMapping
    @Operation(
            summary = "Listar regiões",
            description = "Retorna todas as regiões ambientais cadastradas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Regiões listadas com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<RegiaoResponse>>> listarTodos() {
        List<EntityModel<RegiaoResponse>> regioes = regiaoService.listarTodos()
                .stream()
                .map(regiaoAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<RegiaoResponse>> collection = CollectionModel.of(
                regioes,
                linkTo(methodOn(RegiaoController.class).listarTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
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
    public ResponseEntity<EntityModel<RegiaoResponse>> buscarPorId(@PathVariable Long id) {
        RegiaoResponse response = regiaoService.buscarPorId(id);
        return ResponseEntity.ok(regiaoAssembler.toModel(response));
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
    public ResponseEntity<EntityModel<RegiaoResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid RegiaoRequest request
    ) {
        RegiaoResponse response = regiaoService.atualizar(id, request);
        return ResponseEntity.ok(regiaoAssembler.toModel(response));
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