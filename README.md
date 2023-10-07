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