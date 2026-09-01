# 🐾 PetShop API

API REST para gerenciamento de pet shop, com vendas, estoque, financeiro, clientes, animais e consultas veterinárias.

## Principais regras

- A conclusão de uma venda baixa o estoque e gera os lançamentos financeiros na mesma transação.
- O preço da venda é obtido do produto cadastrado, não do corpo da requisição.
- Movimentações concorrentes de estoque utilizam lock pessimista e controle otimista de versão.
- Agendamentos impedem conflitos do veterinário e do cliente.
- Consultas canceladas liberam o horário.
- Cancelar uma venda devolve os itens ao estoque e remove parcelas ainda não pagas.

## Tecnologias

- Java 21 e Spring Boot 3
- Spring Data JPA e PostgreSQL
- Spring Security, JWT e BCrypt
- Flyway
- MapStruct e Lombok
- OpenAPI/Swagger
- JUnit 5, Mockito, AssertJ e Testcontainers
- Docker e Docker Compose

## Executando com Docker

1. Copie `.env.example` para `.env`.
2. Troque todas as senhas e gere uma chave JWT Base64 com pelo menos 32 bytes.
3. Execute:

```bash
docker compose up --build
```

Serviços:

- API: http://localhost:8083
- Swagger: http://localhost:8083/swagger-ui/index.html
- Adminer: http://localhost:8081

No Adminer, utilize servidor `postgres`, porta `5432` e as credenciais definidas no `.env`.

O administrador inicial é criado somente quando `ADMIN_EMAIL` e `ADMIN_PASSWORD` estiverem preenchidos. Contas registradas pela API recebem `ROLE_USER` e possuem acesso de leitura. Operações de escrita exigem `ROLE_ADMIN`.

## Executando localmente

Suba apenas a infraestrutura:

```bash
docker compose up -d postgres adminer
```

Defina `JWT_SECRET`, `DB_URL`, `DB_USERNAME` e `DB_PASSWORD`. Depois execute:

```bash
./gradlew bootRun
```

No Windows:

```cmd
gradlew.bat bootRun
```

## Testes

```bash
./gradlew test
```

Os testes unitários usam H2 em modo de compatibilidade com PostgreSQL.

## Banco de dados

O esquema é controlado pelo Flyway em `src/main/resources/db/migration`. O Hibernate utiliza `ddl-auto: validate`, portanto mudanças nas entidades devem ser acompanhadas de uma nova migration.

## Segurança

O arquivo `.env` está ignorado pelo Git; use apenas `.env.example` como modelo.
