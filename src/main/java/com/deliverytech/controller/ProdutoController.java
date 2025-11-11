package com.deliverytech.controller;

import com.deliverytech.api.ApiResponse;
import com.deliverytech.model.Produto;
import com.deliverytech.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@Tag(name = "Produtos", description = "CRUD de produtos")
public class ProdutoController {

    private final ProdutoService service;

    public ProdutoController(ProdutoService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Cadastrar produto")
    public ResponseEntity<ApiResponse<Produto>> cadastrar(@RequestBody Produto produto) {
        var saved = service.save(produto);
        return ResponseEntity.created(URI.create("/api/produtos/" + saved.getId())).body(ApiResponse.created(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> buscar(@PathVariable Long id) {
        return service.findById(id).map(p -> ResponseEntity.ok(ApiResponse.ok(p)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Produto não encontrado")));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Produto>> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Produto não encontrado"));
        var p = opt.get();
        p.setNome(produto.getNome()); p.setPreco(produto.getPreco()); p.setDisponivel(produto.isDisponivel());
        service.save(p);
        return ResponseEntity.ok(ApiResponse.ok(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/disponibilidade")
    public ResponseEntity<ApiResponse<Produto>> toggle(@PathVariable Long id) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Produto não encontrado"));
        var p = opt.get();
        p.setDisponivel(!p.isDisponivel());
        service.save(p);
        return ResponseEntity.ok(ApiResponse.ok(p));
    }

    @GetMapping("/restaurante/{restauranteId}/produtos")
    public ResponseEntity<ApiResponse<List<Produto>>> produtosDoRestaurante(@PathVariable Long restauranteId,
                                                                            @RequestParam(required = false) Boolean disponivel) {
        var list = service.findByRestaurante(restauranteId, disponivel);
        return ResponseEntity.ok(ApiResponse.ok(list));
    }

    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<ApiResponse<List<Produto>>> porCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(ApiResponse.ok(service.findByCategoria(categoria)));
    }

    @GetMapping("/buscar")
    public ResponseEntity<ApiResponse<List<Produto>>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(ApiResponse.ok(service.searchByName(nome)));
    }
}
