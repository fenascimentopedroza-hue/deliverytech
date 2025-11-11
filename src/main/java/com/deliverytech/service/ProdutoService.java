package com.deliverytech.service;

import com.deliverytech.model.Produto;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ProdutoService {
    private final Map<Long, Produto> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public ProdutoService() {
        save(new Produto(null, 1L, "Margherita", 25.0));
        save(new Produto(null, 1L, "Pepperoni", 30.0));
        save(new Produto(null, 2L, "Sushi Combo", 50.0));
    }

    public Produto save(Produto p) {
        if (p.getId() == null) p.setId(seq.getAndIncrement());
        store.put(p.getId(), p);
        return p;
    }

    public Optional<Produto> findById(Long id) { return Optional.ofNullable(store.get(id)); }

    public List<Produto> findByRestaurante(Long restauranteId, Boolean disponivel) {
        return store.values().stream()
                .filter(p -> restauranteId == null || p.getRestauranteId().equals(restauranteId))
                .filter(p -> disponivel == null || p.isDisponivel() == disponivel)
                .toList();
    }

    public List<Produto> findByCategoria(String categoria) {
        // dummy: ignore category -> return all
        return store.values().stream().toList();
    }

    public List<Produto> searchByName(String nome) {
        return store.values().stream().filter(p -> p.getNome().toLowerCase().contains(nome.toLowerCase())).toList();
    }

    public void delete(Long id) { store.remove(id); }
}
