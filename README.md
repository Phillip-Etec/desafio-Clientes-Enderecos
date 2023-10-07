# Desafio Backend Muralis Java
Esse repositório contém a solução para o desafio back-end da Muralis de Java Spring Boot.
O projeto é uma API restful, que recebe requests HTTP e atualiza um banco de dados PostgresSQL.
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

Após a configuração, basta enviar requests HTTP para a porta que a API está escutando, como:
- Create
    ```
    curl --location --request POST 'http://localhost:9090/api/clientes' --header 'Content-Type: application/json' --data-raw '{"nome":"nome"}'
    ```
- Read
    ```
    curl --location --request GET 'http://localhost:9090/api/clientes'
    ```
- Update
    ```
    id=1
    curl --location --request PUT 'http://localhost:9090/api/clientes/$id' --header 'Content-Type: application/json' --data-raw '{"nome":"novo nome", "dataCadastro":"2022-02-28 12:20:13"}'
    ```
- Delete
    ```
    id=1
    curl --location --request GET 'http://localhost:9090/api/clientes/$id'
    ```
___
### Banco de Dados

Esse é o banco de dados implementado em postgres:
```
DROP TABLE IF EXISTS enderecos;
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

INSERT INTO enderecos (cep, logradouro, cidade, numero, complemento, cliente_id)
VALUES ('08440-420', 'Rua Acutinga', 'São Paulo', '11', 'Bloco D, apartamento 55', 1);

SELECT
	cli_id, cli_nome, cli_data_cadastro
FROM
	clientes
INNER JOIN enderecos ON cli_id = cliente_id
WHERE cli_id=2;

SELECT
	id, cep, logradouro, numero, complemento, cliente_id
FROM
	enderecos
JOIN clientes ON cliente_id = cli_id
WHERE cliente_id=2;

UPDATE enderecos SET 
    cep = '08440-420', 
    logradouro = 'Rua Acutinga', 
    cidade = 'São Paulo', 
    numero = '11', 
    complemento = 'Bloco C, apartamento 33'
WHERE id=1;
```
___
### APIs
A seguir estão as tabelas das APIs fornecidas:

#### API de Clientes:
|  Método  | Url |  Ação  | Exemplo Corpo JSON | Retorno |
| :------: | :-- | :----- | :--------          | :------ |
| POST | /api/clientes | Criar um novo cliente | `{"nome":"nome", "dataCadastro":"2023-07-04 00:00:00"}` | Mensagem de sucesso ou erro |
| GET | /api/clientes | Retornar todos os clientes | ` ` | Array de Objetos Cliente |
| GET | /api/clientes/{id} | Retornar um cliente pelo id | ` ` | Objeto Cliente |
| GET | /api/clientes?nome={nome} | Retornar todos os clientes com esse {nome} | ` ` | Array de Objetos Cliente | Mensagem de sucesso ou erro |
| PUT | /api/clientes/{id} | Atualizar um cliente pelo | `{"nome":"novo nome", "dataCadastro":"2023-02-15 00:00:00"}` | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/{id} | Deletar um cliente pelo id | ` ` | | Mensagem de sucesso ou erro |
| DELETE | /api/clientes/ | Deletar todos os clientes | ` ` | Mensagem de sucesso ou erro |
