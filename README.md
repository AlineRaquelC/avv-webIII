# AVV Web III - Automanager

Projeto desenvolvido para a disciplina de Web III.

## Tecnologias

- Java 17
- Spring Boot
- Maven
- H2 Database
- JWT
- Docker

## Arquitetura

O projeto foi separado em microservicos REST. A aplicacao `api` funciona como gateway, recebe as requisicoes externas, valida o JWT e se comunica com os demais microservicos via HTTP.

## Microservicos

| Microservico | Porta | Responsabilidade |
|---|---:|---|
| api | 8080 | Gateway/API principal com JWT |
| auth-service | 8081 | Login de usuarios |
| clientes-service | 8082 | Clientes por empresa |
| funcionarios-service | 8083 | Funcionarios por empresa |
| servicos-service | 8084 | Servicos e mercadorias por empresa |
| veiculos-service | 8085 | Veiculos atendidos por empresa |
| vendas-service | 8086 | Vendas por empresa em periodo |

## Como executar com Docker

Requisito:

- Docker instalado e em execucao

Na raiz do projeto, execute:

```bash
docker compose up --build
```

No Windows, tambem pode executar:

```text
start.bat
```

Para encerrar:

```bash
docker compose down
```

No Windows, tambem pode executar:

```text
stop.bat
```

A API principal ficara disponivel em:

```text
http://localhost:8080
```

## Autenticacao

Primeiro faca login:

```http
POST http://localhost:8080/login
```

Body JSON:

```json
{
  "login": "admin",
  "senha": "123"
}
```

A resposta retorna um token JWT:

```json
{
  "token": "..."
}
```

Use o token nas rotas protegidas com o header:

```http
Authorization: Bearer SEU_TOKEN
```

## Rotas protegidas da API

```text
GET http://localhost:8080/api/empresas
GET http://localhost:8080/api/empresas/1/clientes
GET http://localhost:8080/api/empresas/1/funcionarios
GET http://localhost:8080/api/empresas/1/servicos-mercadorias
GET http://localhost:8080/api/empresas/1/vendas?dataInicio=2026-06-01&dataFim=2026-06-30
GET http://localhost:8080/api/empresas/1/veiculos
```

Sem token, as rotas `/api/**` retornam acesso negado.

## Easter Egg

```text
GET http://localhost:8080/easter-egg
```

## Teste de comunicacao entre microservicos

Para confirmar que a `api` se comunica com microservicos separados, pare um servico especifico:

```bash
docker compose stop funcionarios-service
```

Depois teste:

```http
GET http://localhost:8080/api/empresas/1/funcionarios
```

Essa rota deve falhar, pois depende do `funcionarios-service`.

As demais rotas, como clientes ou veiculos, continuam funcionando se seus respectivos servicos estiverem ativos.

Para subir novamente:

```bash
docker compose start funcionarios-service
```