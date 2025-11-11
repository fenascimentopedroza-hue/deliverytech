package com.deliverytech.model;

public class Restaurante {
    private Long id;
    private String nome;
    private String categoria;
    private boolean ativo = true;
    private Double taxaEntrega = 0.0;

    public Restaurante() {}
    public Restaurante(Long id, String nome, String categoria, Double taxaEntrega) {
        this.id = id; this.nome = nome; this.categoria = categoria; this.taxaEntrega = taxaEntrega;
    }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
    public Double getTaxaEntrega() { return taxaEntrega; }
    public void setTaxaEntrega(Double taxaEntrega) { this.taxaEntrega = taxaEntrega; }
}
