package com.deliverytech.service;

import com.deliverytech.model.Restaurante;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class RestauranteService {
    private final Map<Long, Restaurante> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public RestauranteService() {
        // seed
        save(new Restaurante(null, "Pizza Express", "Pizza", 5.0));
        save(new Restaurante(null, "Sushi House", "Japonesa", 7.0));
        save(new Restaurante(null, "Burger Star", "FastFood", 4.0));
    }

    public Restaurante save(Restaurante r) {
        if (r.getId() == null) r.setId(seq.getAndIncrement());
        store.put(r.getId(), r);
        return r;
    }

    public Optional<Restaurante> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Restaurante> findAll(String categoria, Boolean ativo, int page, int size) {
        return store.values().stream()
                .filter(r -> categoria == null || r.getCategoria().equalsIgnoreCase(categoria))
                .filter(r -> ativo == null || r.isAtivo() == ativo)
                .skip((long) page * size)
                .limit(size)
                .collect(Collectors.toList());
    }

    public void delete(Long id) { store.remove(id); }

    public double calcularTaxa(Long id, String cep) {
        // dummy calculation: base taxa + distance approximation from cep digits
        var r = store.get(id);
        if (r == null) throw new NoSuchElementException("Restaurante não encontrado");
        int sum = cep.chars().filter(Character::isDigit).sum();
        return r.getTaxaEntrega() + (sum % 5);
    }

    public List<Restaurante> proximos(String cep) {
        return store.values().stream().limit(5).toList();
    }
}
