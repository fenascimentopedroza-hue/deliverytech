package com.deliverytech.service;

import com.deliverytech.model.Pedido;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PedidoService {
    private final Map<Long, Pedido> store = new LinkedHashMap<>();
    private final AtomicLong seq = new AtomicLong(1);

    public Pedido save(Pedido p) {
        if (p.getId() == null) p.setId(seq.getAndIncrement());
        store.put(p.getId(), p);
        return p;
    }

    public Optional<Pedido> findById(Long id) { return Optional.ofNullable(store.get(id)); }
    public List<Pedido> findAll() { return new ArrayList<>(store.values()); }
    public void delete(Long id) { store.remove(id); }
}
