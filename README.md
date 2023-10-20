# Desafio Backend Muralis Java
Esse repositório contém a solução para o desafio back-end da Muralis de Java Spring Boot.
O projeto é uma API restful, que recebe requests HTTP e manipula um banco de dados PostgresSQL.

De forma resumida, com essa API é possível gerenciar clientes cadastrados, além de poder gerenciar endereços e contatos dos respectivos clientes, com a utilização da API externa [viacep](cep.com.br/) para preencher os dados do endereço automaticamente. 
___
## Iniciando
Dependências:
* git
* JDK versão 20 ou mais atual
* Maven versão 3.8.7

Para iniciar o projeto, basta clonar esse repositório e rodá-lo com o maven:
```bash
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

Após a configuração, basta enviar requests HTTP para a porta que a API está escutando. Enviar requests HTTP podem ser feitas tanto com GUIs, como o [Postman](https://www.postman.com/), quanto por utilidades de linha de comando, como o [curl](https://curl.se/), e aqui estão alguns exemplos de como usá-lo para fazer requests à esta API:
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
    id=1 && curl --location --request PUT "http://localhost:9090/api/clientes/$id" --header 'Content-Type: application/json' --data-raw '{"nome":"novo nome", "dataCadastro":"2022-02-28 12:20:13"}'
    ```
- Delete
    ```bash
    id=1 && curl --location --request GET "http://localhost:9090/api/clientes/$id"
    ```
___
### Banco de Dados

Esse é o banco de dados implementado em postgres:
```sql
DROP TABLE IF EXISTS enderecos;
DROP TABLE IF EXISTS contatos;
DROP TABLE IF EXISTS tipo_contato;
DROP TABLE IF EXISTS clientes;


CREATE TABLE clientes
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    nome VARCHAR(255) NOT NULL,
    data_cadastro TIMESTAMP NOT NULL
);

CREATE TABLE tipo_contato
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    tipo VARCHAR(255) NOT NULL
);

CREATE TABLE enderecos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    cep VARCHAR(255) NOT NULL,
    logradouro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    numero VARCHAR(255) NOT NULL,
    complemento VARCHAR(255) NOT NULL,
    idcliente BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(idcliente)
            REFERENCES clientes(id)
);

CREATE TABLE contatos
(
    id BIGSERIAL PRIMARY KEY NOT NULL,
    idtipocontato BIGSERIAL NOT NULL,
    texto VARCHAR(255) NOT NULL,
    idcliente BIGSERIAL NOT NULL,
    CONSTRAINT fk_cliente
        FOREIGN KEY(idcliente)
            REFERENCES clientes(id),
    CONSTRAINT fk_tipo_contato
        FOREIGN KEY(idtipocontato)
            REFERENCES tipo_contato(id)
);
```
Tipos de contatos já cadastrados:
|  id  | tipo            |
| :--: | :-------------- |
|  1   | 'e-mail'        |
|  2   | 'telefone fixo' |
|  3   | 'celular'       |
|  4   | 'whatsapp'      |
|  5   | 'website'       |
___

## APIs

A seguir estão as tabelas explicando os endpoints fornecidos:


### Endpoints de Clientes:

| Verbo HTTP  | Path |  Ação  | Exemplo Corpo JSON | Função |
| :---------: | :--- | :----- | :--------          | :----- |
| POST   | /api/clientes | Criar um novo cliente | `{"nome":"nome", "dataCadastro":"2023-07-04 00:00:00"}` | Mensagem de sucesso ou erro |
|  GET   | /api/clientes | Retornar todos os clientes | ` ` | Array de Objetos Cliente | GET | /api/clientes/{id} | Retornar um cliente pelo id | ` ` | Objeto Cliente |
| GET    | /api/clientes?nome={nome} | Retornar todos os clientes com esse {nome} | ` ` | Array de Objetos Cliente | Mensagem de sucesso ou erro |
| PUT    | /api/clientes/{id} | Atualizar um cliente pelo | `{"nome":"novo nome", "dataCadastro":"2023-02-15 00:00:00"}` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |

### Endpoints de Endereços:

| Verbo HTTP  | Path |  Ação  | Exemplo Corpo JSON | Função |
| :---------: | :--- | :----- | :--------          | :----- |
| POST   | /api/enderecos | Criar um novo endereço | `{"cep":"010010","complemento":"Segundo Andar","numero":"12B","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET   | /api/enderecos | Retornar todos os enderecos | ` ` | Array de Objetos Cliente | GET | /api/enderecos/{id} | Retornar um endereco pelo id | ` ` | Objeto Cliente |
| PUT    | /api/enderecos/{id} | Atualizar um endereco pelo {id} | `{"cep":"010010","complemento":"","numero":"9","idCliente":2}` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/{id} | Deletar um endereco pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/enderecos/ | Deletar todos os enderecos | ` ` | Mensagem de sucesso ou erro |
| GET    | /api/enderecoviacep?cep={cep} | Pesquisar um endereço pelo CEP {cep} na API viacep | ` ` | Objeto EnderecoViacep |

### Endpoints de Contatos:

| Verbo HTTP  | Path |  Ação  | Exemplo Corpo JSON | Função |
| :---------: | :--- | :----- | :--------          | :----- |
| POST   | /api/contatos | Criar um novo contato | `{"texto":"guilherme.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
|  GET   | /api/contatos | Retornar todos os contatos | ` ` | Array de Objetos Contato | GET | /api/contatos/{id} | Retornar um contato pelo id | ` ` | Objeto Contato |
| PUT    | /api/contatos/{id} | Atualizar um contato pelo id | `{"texto":"william.2753gmail.com","tipo":"email","idCliente":1}` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/{id} | Deletar um cliente pelo id | ` ` | Mensagem de sucesso ou erro |
| DELETE | /api/contatos/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |
