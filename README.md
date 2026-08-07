# API de Produtos — Spring Boot

API REST simples para gerenciamento de produtos (CRUD), desenvolvida com Spring Boot e persistência em SQLite via Spring Data JPA / Hibernate.

## Tecnologias

- **Java 22**
- **Spring Boot 3.2.5**
  - Spring Web
  - Spring Data JPA
  - Spring Boot Validation
  - Spring Boot DevTools
- **Hibernate Community Dialects** (suporte ao dialeto SQLite)
- **SQLite** (`sqlite-jdbc`) como banco de dados
- **Maven** (com Maven Wrapper — `mvnw` / `mvnw.cmd`)

## Estrutura do projeto

```
src/main/java/com/produtoapi/
├── MeuProjetoSpringbootApplication.java   # Classe principal (entry point)
├── controller/
│   └── ProdutoController.java             # Endpoints REST (/produtos)
├── model/
│   └── Produto.java                       # Entidade JPA
├── repository/
│   └── ProdutoRepository.java             # Interface JpaRepository
└── service/
    └── ProdutoService.java                # Regras de negócio

src/main/resources/
└── application.properties                 # Configurações da aplicação e do banco
```

A aplicação segue a arquitetura em camadas clássica do Spring: **Controller → Service → Repository → Model**.

## Modelo de dados

A entidade `Produto` possui os seguintes campos:

| Campo        | Tipo     | Observações                          |
|--------------|----------|---------------------------------------|
| `id`         | `long`   | Chave primária, gerada automaticamente |
| `nome`       | `String` | Obrigatório (`@NotEmpty`)              |
| `quantidade` | `int`    |                                        |
| `preco`      | `double` |                                        |
| `status`     | `String` |                                        |

## Endpoints da API

Base path: `/produtos`

| Método   | Endpoint         | Descrição                          |
|----------|------------------|--------------------------------------|
| `GET`    | `/produtos`      | Lista todos os produtos              |
| `GET`    | `/produtos/{id}` | Busca um produto pelo ID             |
| `POST`   | `/produtos`      | Cadastra um novo produto             |
| `PUT`    | `/produtos/{id}` | Atualiza um produto existente        |
| `DELETE` | `/produtos/{id}` | Remove um produto pelo ID            |

### Exemplo de requisição — Criar produto (`POST /produtos`)

```json
{
  "nome": "Teclado Mecânico",
  "quantidade": 10,
  "preco": 249.90,
  "status": "disponível"
}
```

## Configuração

O arquivo `application.properties` define:

- Nome da aplicação: `meu-projeto-springboot`
- Banco de dados: SQLite, armazenado no arquivo `meu_banco_de_dados.db`
- Dialeto Hibernate: `org.hibernate.community.dialect.SQLiteDialect`
- `spring.jpa.hibernate.ddl-auto=update` — o schema do banco é criado/atualizado automaticamente a partir das entidades
- `spring.jpa.show-sql=true` — exibe as queries SQL executadas no console

Não é necessário nenhum servidor de banco de dados externo: o arquivo `.db` é criado automaticamente na raiz do projeto na primeira execução.

## Como executar

### Pré-requisitos

- Java 22 instalado
- Não é necessário ter o Maven instalado globalmente — o projeto inclui o Maven Wrapper

### Passos

```bash
# Clonar o repositório
git clone https://github.com/DaviOrtolan/APISpringBoot.git
cd APISpringBoot

# Executar a aplicação (Linux/macOS)
./mvnw spring-boot:run

# Executar a aplicação (Windows)
mvnw.cmd spring-boot:run
```

A API ficará disponível em `http://localhost:8080/produtos`.

### Rodando os testes

```bash
./mvnw test
```

## Possíveis melhorias

- Adicionar tratamento de exceções centralizado (`@ControllerAdvice`) — atualmente um produto inexistente na atualização lança `RuntimeException` genérica
- Retornar códigos HTTP mais apropriados (ex.: `404 Not Found` quando o produto não existe, `201 Created` ao cadastrar)
- Usar DTOs em vez de expor a entidade `Produto` diretamente nos endpoints
- Adicionar documentação interativa com Swagger/OpenAPI
- Adicionar testes unitários e de integração para controller, service e repository

## Autor

Desenvolvido por [Davi Ortolan](https://github.com/DaviOrtolan).
