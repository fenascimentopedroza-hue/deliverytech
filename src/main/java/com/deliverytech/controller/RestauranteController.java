package com.deliverytech.controller;

import com.deliverytech.api.ApiResponse;
import com.deliverytech.dto.RestauranteDTO;
import com.deliverytech.model.Restaurante;
import com.deliverytech.service.RestauranteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/restaurantes")
@Tag(name = "Restaurantes", description = "Operações relacionadas aos restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Cadastrar restaurante")
    public ResponseEntity<ApiResponse<Restaurante>> cadastrar(@Valid @RequestBody RestauranteDTO dto) {
        Restaurante r = new Restaurante(null, dto.getNome(), dto.getCategoria(), dto.getTaxaEntrega());
        var saved = service.save(r);
        return ResponseEntity.created(URI.create("/api/restaurantes/" + saved.getId())).body(ApiResponse.created(saved));
    }

    @GetMapping
    @Operation(summary = "Listar restaurantes com filtros")
    public ResponseEntity<ApiResponse<List<Restaurante>>> listar(
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) Boolean ativo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        var list = service.findAll(categoria, ativo, page, size);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar restaurante por id")
    public ResponseEntity<ApiResponse<Restaurante>> buscar(@PathVariable Long id) {
        return service.findById(id)
                .map(r -> ResponseEntity.ok(ApiResponse.ok(r)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND", "Restaurante não encontrado")));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar restaurante")
    public ResponseEntity<ApiResponse<Restaurante>> atualizar(@PathVariable Long id, @Valid @RequestBody RestauranteDTO dto) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Restaurante não encontrado"));
        var r = opt.get();
        r.setNome(dto.getNome()); r.setCategoria(dto.getCategoria()); r.setTaxaEntrega(dto.getTaxaEntrega());
        service.save(r);
        return ResponseEntity.ok(ApiResponse.ok(r));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Ativar/desativar restaurante")
    public ResponseEntity<ApiResponse<Restaurante>> toggle(@PathVariable Long id) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Restaurante não encontrado"));
        var r = opt.get();
        r.setAtivo(!r.isAtivo());
        service.save(r);
        return ResponseEntity.ok(ApiResponse.ok(r));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ApiResponse<List<Restaurante>>> porCategoria(@PathVariable String categoria) {
        var list = service.findAll(categoria, null, 0, 50);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/{id}/taxa-entrega/{cep}")
    public ResponseEntity<ApiResponse<Double>> taxaEntrega(@PathVariable Long id, @PathVariable String cep) {
        try {
            double taxa = service.calcularTaxa(id, cep);
            return ResponseEntity.ok(ApiResponse.ok(taxa));
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND", ex.getMessage()));
        }
    }

    @GetMapping("/proximos/{cep}")
    public ResponseEntity<ApiResponse<List<Restaurante>>> proximos(@PathVariable String cep) {
        var list = service.proximos(cep);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }
}
