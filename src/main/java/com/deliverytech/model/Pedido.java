package com.deliverytech.model;

import java.time.Instant;
import java.util.List;
public class Pedido {
    private Long id;
    private Long clienteId;
    private Long restauranteId;
    private String enderecoEntrega;
    private List<Item> itens;
    private String status = "CRIADO";
    private Instant criadoEm = Instant.now();

    public static class Item { public Long produtoId; public int quantidade; }

    public Pedido() {}
    public Long getId() { return id; } public void setId(Long id) { this.id = id; }
    public Long getClienteId() { return clienteId; } public void setClienteId(Long clienteId) { this.clienteId = clienteId; }
    public Long getRestauranteId() { return restauranteId; } public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
    public String getEnderecoEntrega() { return enderecoEntrega; } public void setEnderecoEntrega(String enderecoEntrega) { this.enderecoEntrega = enderecoEntrega; }
    public List<Item> getItens() { return itens; } public void setItens(List<Item> itens) { this.itens = itens; }
    public String getStatus() { return status; } public void setStatus(String status) { this.status = status; }
    public Instant getCriadoEm() { return criadoEm; } public void setCriadoEm(Instant criadoEm) { this.criadoEm = criadoEm; }
}
