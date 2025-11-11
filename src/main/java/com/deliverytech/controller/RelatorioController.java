package com.deliverytech.controller;

import com.deliverytech.api.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
@Tag(name = "Relatórios", description = "Relatórios do sistema")
public class RelatorioController {

    @GetMapping("/vendas-por-restaurante")
    public ResponseEntity<ApiResponse<Map<String,Object>>> vendasPorRestaurante(@RequestParam String dataInicio, @RequestParam String dataFim) {
        // dummy response
        return ResponseEntity.ok(ApiResponse.ok(Map.of("restauranteId", 1, "total", 1234.5, "dataInicio", dataInicio, "dataFim", dataFim)));
    }

    @GetMapping("/produtos-mais-vendidos")
    public ResponseEntity<ApiResponse<Object>> topProdutos() {
        return ResponseEntity.ok(ApiResponse.ok(Map.of("top", Map.of(1,"Margherita",2,"Pepperoni"))));
    }

    @GetMapping("/clientes-ativos")
    public ResponseEntity<ApiResponse<Object>> clientesAtivos() {
        return ResponseEntity.ok(ApiResponse.ok(Map.of("clientesAtivos", 42)));
    }

    @GetMapping("/pedidos-por-periodo")
    public ResponseEntity<ApiResponse<Object>> pedidosPorPeriodo(@RequestParam String inicio, @RequestParam String fim) {
        return ResponseEntity.ok(ApiResponse.ok(Map.of("count", 10, "inicio", inicio, "fim", fim)));
    }
}
