# DeliveryTech - Projeto Spring Boot (entrega para atividade)

Este projeto é uma implementação **minimalista** e funcional de uma API REST para fins acadêmicos, baseada no roteiro que você enviou.

### O que está incluído
- Controllers: Restaurante, Produto, Pedido, Relatórios
- Serviços em memória (para testar sem banco)
- Padronização de respostas via `ApiResponse<T>`
- Swagger/OpenAPI configurado (springdoc)
- Exemplos de endpoints e payloads
- Testes básicos (ver pasta `src/test`)

### Como rodar
Requisitos: Java 17, Maven

```bash
mvn -v
mvn clean package
mvn spring-boot:run
```

A API estará disponível em `http://localhost:8080/api`  
Swagger UI: `http://localhost:8080/swagger-ui.html`  
API docs: `http://localhost:8080/api-docs`

### Notas
- Implementação intencionalmente simples para facilitar entendimento e validação pelo professor.
- Pode ser estendida para usar JPA, autenticação, testes mais completos e integração real com gateways.

