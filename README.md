# 🐾 PetShop API

Uma API RESTful para o gerenciamento de um PetShop. O sistema orquestra desde o controle de estoque e fluxo de vendas até o agendamento de consultas veterinárias e gestão financeira automatizada.

## 📋 Sobre o Projeto

Este projeto foi desenvolvido para testar meus estudos. O sistema não apenas realiza CRUDs básicos, mas gerencia o ciclo de vida das operações comerciais:

* **Vendas:** Ao finalizar uma venda, o sistema automaticamente baixa o estoque e gera os registros financeiros (contas a receber).
* **Agendamentos:** Validação inteligente de conflitos de horários para veterinários.
* **Financeiro:** Geração automática de parcelas e controle de pagamentos parciais ou totais.


## 📖 Documentação da API (Swagger)

A API utiliza Swagger UI (OpenAPI 3) para documentação interativa. Com a aplicação rodando, acesse:

* **URL:** http://localhost:8082/swagger-ui/index.html

Como testar endpoints protegidos:

1. Obter Token: Utilize o endpoint de login para gerar um JWT.

2. Autorizar: No topo da página do Swagger, clique no botão "Authorize".

3. Configurar: Insira o token gerado.

### 🗄️ Gerenciamento do Banco de Dados

Além do PostgreSQL, o ambiente Docker inclui o **Adminer** para visualização das tabelas:
* **Acesso:** `http://localhost:8081`
* **Servidor:** `host.docker.internal:5433`

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Framework:** Spring Boot 3 (Web, Data JPA, Validation)
* **Segurança:** Spring Security + JWT (JSON Web Token)
* **Banco de Dados:** PostgreSQL (Produção/Dev)
* **Documentação:** Springdoc OpenAPI 3
* **Build Tool:** Gradle
* **Mapeamento:** MapStruct
* **Utilitários:** Lombok
* **Containerização:** Docker & Docker Compose
* **Testes:** JUnit 5, Mockito, AssertJ

## 📦 Como Rodar o Projeto

### Pré-requisitos
* Java 21+ instalado.
* Docker e Docker Compose (Opcional, mas recomendado para o Banco de Dados).

### Passo a Passo

1.  **Clone o repositório:**
    ```bash
    git clone git clone https://github.com/fabiano-fazan/petshop-api.git
    ```

2.  **Configuração do Banco de Dados:**
    O projeto já possui um arquivo `docker-compose.yml`. Para subir o PostgreSQL:
    ```bash
    docker-compose up -d
    ```

3.  **Execute a aplicação:**
    Utilize o wrapper do Gradle (não é necessário ter o Gradle instalado globalmente).
    * **Windows:**
     ```cmd
     gradlew.bat bootRun
     ```

A API estará disponível em: `http://localhost:8082`

## 🧪 Rodando os Testes

O projeto conta com uma suíte abrangente de testes unitários cobrindo Services, Generators e Validadores.

Para executar os testes:

```bash
./gradlew test
