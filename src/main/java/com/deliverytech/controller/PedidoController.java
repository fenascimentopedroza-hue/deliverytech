package com.deliverytech.controller;

import com.deliverytech.api.ApiResponse;
import com.deliverytech.model.Pedido;
import com.deliverytech.service.PedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedidos", description = "Operações com pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) { this.service = service; }

    @PostMapping
    @Operation(summary = "Criar pedido")
    public ResponseEntity<ApiResponse<Pedido>> criar(@RequestBody Pedido pedido) {
        var saved = service.save(pedido);
        return ResponseEntity.created(URI.create("/api/pedidos/" + saved.getId())).body(ApiResponse.created(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Pedido>> buscar(@PathVariable Long id) {
        return service.findById(id).map(p -> ResponseEntity.ok(ApiResponse.ok(p)))
                .orElseGet(() -> ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Pedido não encontrado")));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Pedido>>> listar() {
        return ResponseEntity.ok(ApiResponse.ok(service.findAll()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Pedido>> atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        var opt = service.findById(id);
        if (opt.isEmpty()) return ResponseEntity.status(404).body(ApiResponse.error("ENTITY_NOT_FOUND","Pedido não encontrado"));
        var p = opt.get();
        p.setStatus(status);
        service.save(p);
        return ResponseEntity.ok(ApiResponse.ok(p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/calcular")
    public ResponseEntity<ApiResponse<Double>> calcular(@RequestBody Pedido pedido) {
        // dummy total: sum of item quantities * 10
        double total = pedido.getItens() == null ? 0 : pedido.getItens().stream().mapToDouble(i -> i.quantidade * 10.0).sum();
        return ResponseEntity.ok(ApiResponse.ok(total));
    }
}
