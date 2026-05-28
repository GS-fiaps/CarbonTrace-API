package br.com.fiap.carbontrace.controller;

import br.com.fiap.carbontrace.assembler.OrgaoAmbientalAssembler;
import br.com.fiap.carbontrace.dto.request.OrgaoAmbientalRequest;
import br.com.fiap.carbontrace.dto.response.OrgaoAmbientalResponse;
import br.com.fiap.carbontrace.service.OrgaoAmbientalService;
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
@RequestMapping("/orgaos-ambientais")
@RequiredArgsConstructor
@Tag(name = "Órgãos Ambientais", description = "Endpoints para gerenciamento dos órgãos ambientais responsáveis por receber alertas")
public class OrgaoAmbientalController {

    private final OrgaoAmbientalService orgaoAmbientalService;
    private final OrgaoAmbientalAssembler orgaoAmbientalAssembler;

    @PostMapping
    @Operation(
            summary = "Cadastrar órgão ambiental",
            description = "Cadastra um órgão ambiental vinculado a um estado existente."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Órgão ambiental cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Estado não encontrado")
    })
    public ResponseEntity<EntityModel<OrgaoAmbientalResponse>> cadastrar(
            @RequestBody @Valid OrgaoAmbientalRequest request
    ) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.cadastrar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(orgaoAmbientalAssembler.toModel(response));
    }

    @GetMapping
    @Operation(
            summary = "Listar órgãos ambientais",
            description = "Retorna todos os órgãos ambientais cadastrados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgãos ambientais listados com sucesso")
    })
    public ResponseEntity<CollectionModel<EntityModel<OrgaoAmbientalResponse>>> listarTodos() {
        List<EntityModel<OrgaoAmbientalResponse>> orgaos = orgaoAmbientalService.listarTodos()
                .stream()
                .map(orgaoAmbientalAssembler::toModel)
                .toList();

        CollectionModel<EntityModel<OrgaoAmbientalResponse>> collection = CollectionModel.of(
                orgaos,
                linkTo(methodOn(OrgaoAmbientalController.class).listarTodos()).withSelfRel()
        );

        return ResponseEntity.ok(collection);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar órgão ambiental por ID",
            description = "Retorna os dados de um órgão ambiental específico pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão ambiental encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Órgão ambiental não encontrado")
    })
    public ResponseEntity<EntityModel<OrgaoAmbientalResponse>> buscarPorId(@PathVariable Long id) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.buscarPorId(id);
        return ResponseEntity.ok(orgaoAmbientalAssembler.toModel(response));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar órgão ambiental",
            description = "Atualiza os dados de um órgão ambiental existente e permite alterar o estado vinculado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Órgão ambiental atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos enviados na requisição"),
            @ApiResponse(responseCode = "404", description = "Órgão ambiental ou estado não encontrado")
    })
    public ResponseEntity<EntityModel<OrgaoAmbientalResponse>> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid OrgaoAmbientalRequest request
    ) {
        OrgaoAmbientalResponse response = orgaoAmbientalService.atualizar(id, request);
        return ResponseEntity.ok(orgaoAmbientalAssembler.toModel(response));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Deletar órgão ambiental",
            description = "Remove um órgão ambiental cadastrado pelo ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Órgão ambiental deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Órgão ambiental não encontrado")
    })
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        orgaoAmbientalService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}