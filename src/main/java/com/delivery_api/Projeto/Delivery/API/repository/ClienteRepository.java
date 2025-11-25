package com.delivery_api.Projeto.Delivery.API.repository;

import com.delivery_api.Projeto.Delivery.API.entity.Cliente; // Importe sua entidade
import org.springframework.data.jpa.repository.JpaRepository; // Importe o JpaRepository
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

   Optional<Cliente> findByEmail(String email);

}
