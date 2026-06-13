# AVV Web III - Automanager

Projeto desenvolvido para a disciplina de Web III.

## Tecnologias

- Java
- Spring Boot
- Maven
- H2 Database
- JWT

## Microservicos

O projeto possui dois microservicos:

### clientes-sistema

Responsavel pelos dados do sistema Automanager.

Porta padrao:

```text
8080

Rotas Principais:

GET /empresas
GET /empresas/{id}/clientes
GET /empresas/{id}/funcionarios
GET /empresas/{id}/servicos-mercadorias
GET /empresas/{id}/vendas?dataInicio=2026-06-01&dataFim=2026-06-30
GET /empresas/{id}/veiculos

api
Responsavel por expor as rotas protegidas com JWT e consumir o microservico clientes-sistema.
Porta padrao:
8081

Como executar no Linux
Abra dois terminais.
No primeiro terminal, execute o microservico clientes-sistema:

cd clientes-sistema
chmod +x mvnw
./mvnw spring-boot:run

No segundo terminal, execute o microservico api:
cd api
chmod +x mvnw
./mvnw spring-boot:run

Autenticacao
Primeiro faca login:
POST http://localhost:8081/login

Body JSON:
{
  "login": "admin",
  "senha": "123"
}

A resposta retorna um token JWT:

{
  "token": "..."
}

Use o token nas rotas protegidas com o header:
Authorization: Bearer SEU_TOKEN

Rotas protegidas da API
GET http://localhost:8081/api/empresas
GET http://localhost:8081/api/empresas/1/clientes
GET http://localhost:8081/api/empresas/1/funcionarios
GET http://localhost:8081/api/empresas/1/servicos-mercadorias
GET http://localhost:8081/api/empresas/1/vendas?dataInicio=2026-06-01&dataFim=2026-06-30
GET http://localhost:8081/api/empresas/1/veiculos

Observacoes
O microservico api precisa que o microservico clientes-sistema esteja rodando na porta 8080, pois ele consome suas rotas internamente.
Para testar as rotas protegidas, primeiro gere o token em /login e depois envie esse token como Bearer Token nas requisicoes para /api/....
```
