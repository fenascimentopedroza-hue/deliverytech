package com.deliverytech.model;

public class Produto {
    private Long id;
    private Long restauranteId;
    private String nome;
    private Double preco;
    private boolean disponivel = true;

    public Produto() {}
    public Produto(Long id, Long restauranteId, String nome, Double preco) {
        this.id = id; this.restauranteId = restauranteId; this.nome = nome; this.preco = preco;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getRestauranteId() { return restauranteId; }
    public void setRestauranteId(Long restauranteId) { this.restauranteId = restauranteId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Double getPreco() { return preco; }
    public void setPreco(Double preco) { this.preco = preco; }
    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }
}
