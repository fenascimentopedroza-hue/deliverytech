package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Produto;
import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.repository.ProdutoRepository;
import com.delivery_api.Projeto.Delivery.API.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RestauranteRepository restauranteRepository;


    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRepository restauranteRepository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRepository = restauranteRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }


    public List<Produto> buscarPorRestaurante(Long restauranteId) {

        return produtoRepository.findByRestauranteId(restauranteId);
    }

    public Produto salvar(Produto produto) {

        if (produto.getRestaurante() != null && produto.getRestaurante().getId() != null) {
            Long idRestaurante = produto.getRestaurante().getId();
            Restaurante restaurante = restauranteRepository.findById(idRestaurante)
                    .orElseThrow(() -> new RuntimeException("Restaurante não encontrado!"));

            produto.setRestaurante(restaurante);
        } else {
            throw new RuntimeException("É obrigatório informar o ID do restaurante!");
        }

        return produtoRepository.save(produto);
    }
}