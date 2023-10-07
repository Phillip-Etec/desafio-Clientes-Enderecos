# Desafio Backend Muralis Java
Esse repositório contém a solução para o desafio back-end da Muralis de Java Spring Boot.
O projeto é uma API restful, que recebe requests HTTP e manipula um banco de dados PostgresSQL.
___
## Iniciando
Dependências:
* git
* JDK versão 20 ou mais atual
* Maven versão 3.8.7

Para iniciar o projeto, basta clonar esse repositório e rodá-lo com o maven:
```
git clone https://github.com/Phillip-Etec/desafio-Clientes-Enderecos --depth 1
cd desafio-Clientes-Enderecos
mvn spring-boot:run
```
O arquivo de configuração de conexão com o banco de dados e da porta local está localizado em: `./src/main/resources/application.properties`, mude-o conforme suas necessidades.
```
spring.datasource.url = jdbc:postgresql://localhost:8080/bd
spring.datasource.username = postgres
spring.datasource.password = 123
spring.jpa.hibernate.ddl-auto = update
server.port = 9090

spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
```

Após a configuração, basta enviar requests HTTP para a porta que a API está escutando. Enviar requests HTTP podem ser feitas tanto com GUIs, como o [Postman](https://www.postman.com/), quanto por utilidades de linha de comando, como o curl, e aqui estão alguns exemplos de como usá-lo para fazer requests à esta API:
- Create
    ```bash
    curl --location --request POST 'http://localhost:9090/api/clientes' --header 'Content-Type: application/json' --data-raw '{"nome":"nome"}'
    ```
- Read
    ```bash
    curl --location --request GET 'http://localhost:9090/api/clientes'
    ```
- Update
    ```bash
    id=1
    curl --location --request PUT 'http://localhost:9090/api/clientes/$id' --header 'Content-Type: application/json' --data-raw '{"nome":"novo nome", "dataCadastro":"2022-02-28 12:20:13"}'
    ```
- Delete
    ```bash
    id=1
    curl --location --request GET 'http://localhost:9090/api/clientes/$id'
    ```
___
### Banco de Dados

Esse é o banco de dados implementado em postgres:
```sql
DROP TABLE IF EXISTS enderecos;
DROP TABLE IF EXISTS contatos;
DROP TABLE IF EXISTS clientes;


CREATE TABLE clientes
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP
);

CREATE TABLE enderecos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    cep VARCHAR(255),
    logradouro VARCHAR(255),
    cidade VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    cliente_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES clientes(id)
);

CREATE TABLE contatos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(255),
    texto VARCHAR(255),
    cliente_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES clientes(id)
);
```

___

## APIs

A seguir estão as tabelas explicando as APIs fornecidas:


### API de Clientes:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/clientes | Criar um novo cliente | `{"nome":"nome", "dataCadastro":"2023-07-04 00:00:00"}` | Mensagem de sucesso ou erro |
|  GET | /api/clientes | Retornar todos os clientes | ` ` | Array de Objetos Cliente | GET | /api/clientes/{id} | Retornar um cliente pelo id | ` ` | Objeto Cliente |
| GET | /api/clientes?nome={nome} | Retornar todos os clientes com esse {nome} | ` ` | Array de Objetos Cliente | Mensagem de sucesso ou erro |
| PUT | /api/clientes/{id} | Atualizar um cliente pelo | `{"nome":"novo nome", "dataCadastro":"2023-02-15 00:00:00"}` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |

### API de Endereços:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/enderecos | Criar um novo endereço | `{"cep":"010010","complemento":"Segundo Andar","numero":"12B","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET | /api/enderecos | Retornar todos os enderecos | ` ` | Array de Objetos Cliente | GET | /api/enderecos/{id} | Retornar um endereco pelo id | ` ` | Objeto Cliente |
| PUT | /api/enderecos/{id} | Atualizar um endereco pelo {id} | `{"cep":"010010","complemento":"","numero":"9","idCliente":2}` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/{id} | Deletar um endereco pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/ | Deletar todos os enderecos | ` ` | Mensagem de sucesso ou erro |
| GET | /api/enderecoviacep?cep={cep} | Pesquisar um endereço pelo CEP {cep} na API viacep | ` ` | Objeto EnderecoViacep |

### API de Contatos:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/contatos | Criar um novo contato | `{"texto":"guilherme.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET | /api/contatos | Retornar todos os contatos | ` ` | Array de Objetos Contato | GET | /api/contatos/{id} | Retornar um contato pelo id | ` ` | Objeto Contato |
| PUT | /api/contatos/{id} | Atualizar um contato pelo id | `{"texto":"william.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |# Desafio Backend Muralis Java
Esse repositório contém a solução para o desafio back-end da Muralis de Java Spring Boot.
O projeto é uma API restful, que recebe requests HTTP e manipula um banco de dados PostgresSQL.
___
## Iniciando
Dependências:
* git
* JDK versão 20 ou mais atual
* Maven versão 3.8.7

Para iniciar o projeto, basta clonar esse repositório e rodá-lo com o maven:
```
git clone https://github.com/Phillip-Etec/desafio-Clientes-Enderecos --depth 1
cd desafio-Clientes-Enderecos
mvn spring-boot:run
```
O arquivo de configuração de conexão com o banco de dados e da porta local está localizado em: `./src/main/resources/application.properties`, mude-o conforme suas necessidades.
```
spring.datasource.url = jdbc:postgresql://localhost:8080/bd
spring.datasource.username = postgres
spring.datasource.password = 123
spring.jpa.hibernate.ddl-auto = update
server.port = 9090

spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
```

Após a configuração, basta enviar requests HTTP para a porta que a API está escutando. Enviar requests HTTP podem ser feitas tanto com GUIs, como o [Postman](https://www.postman.com/), quanto por utilidades de linha de comando, como o curl, e aqui estão alguns exemplos de como usá-lo para fazer requests à esta API:
- Create
    ```bash
    curl --location --request POST 'http://localhost:9090/api/clientes' --header 'Content-Type: application/json' --data-raw '{"nome":"nome"}'
    ```
- Read
    ```bash
    curl --location --request GET 'http://localhost:9090/api/clientes'
    ```
- Update
    ```bash
    id=1
    curl --location --request PUT 'http://localhost:9090/api/clientes/$id' --header 'Content-Type: application/json' --data-raw '{"nome":"novo nome", "dataCadastro":"2022-02-28 12:20:13"}'
    ```
- Delete
    ```bash
    id=1
    curl --location --request GET 'http://localhost:9090/api/clientes/$id'
    ```
___
### Banco de Dados

Esse é o banco de dados implementado em postgres:
```sql
DROP TABLE IF EXISTS enderecos;
DROP TABLE IF EXISTS con;
DROP TABLE IF EXISTS clientes;


CREATE TABLE clientes
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP
);

CREATE TABLE enderecos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    cep VARCHAR(255),
    logradouro VARCHAR(255),
    cidade VARCHAR(255),
    numero VARCHAR(255),
    complemento VARCHAR(255),
    cliente_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES clientes(id)
);

CREATE TABLE contatos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(255),
    texto VARCHAR(255),
    cliente_id BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(cliente_id)
        REFERENCES clientes(id)
);
```

___

## APIs

A seguir estão as tabelas explicando as APIs fornecidas:


### API de Clientes:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/clientes | Criar um novo cliente | `{"nome":"nome", "dataCadastro":"2023-07-04 00:00:00"}` | Mensagem de sucesso ou erro |
|  GET | /api/clientes | Retornar todos os clientes | ` ` | Array de Objetos Cliente | GET | /api/clientes/{id} | Retornar um cliente pelo id | ` ` | Objeto Cliente |
| GET | /api/clientes?nome={nome} | Retornar todos os clientes com esse {nome} | ` ` | Array de Objetos Cliente | Mensagem de sucesso ou erro |
| PUT | /api/clientes/{id} | Atualizar um cliente pelo | `{"nome":"novo nome", "dataCadastro":"2023-02-15 00:00:00"}` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |

### API de Endereços:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/enderecos | Criar um novo endereço | `{"cep":"010010","complemento":"Segundo Andar","numero":"12B","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET | /api/enderecos | Retornar todos os enderecos | ` ` | Array de Objetos Cliente | GET | /api/enderecos/{id} | Retornar um endereco pelo id | ` ` | Objeto Cliente |
| PUT | /api/enderecos/{id} | Atualizar um endereco pelo {id} | `{"cep":"010010","complemento":"","numero":"9","idCliente":2}` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/{id} | Deletar um endereco pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/ | Deletar todos os enderecos | ` ` | Mensagem de sucesso ou erro |
| GET | /api/enderecoviacep?cep={cep} | Pesquisar um endereço pelo CEP {cep} na API viacep | ` ` | Objeto EnderecoViacep |

### API de Contatos:

| Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/contatos | Criar um novo contato | `{"texto":"guilherme.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET | /api/contatos | Retornar todos os contatos | ` ` | Array de Objetos Contato | GET | /api/contatos/{id} | Retornar um contato pelo id | ` ` | Objeto Contato |
| PUT | /api/contatos/{id} | Atualizar um contato pelo id | `{"texto":"william.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |
