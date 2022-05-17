# User Manager API

Recursos disponíveis para acesso via API:
* [**Criação de Usuário**](#reference)
* [**Consulta de Usuários Cadastrados**](#reference)
* [**Edição dos dados do Usuário**](#reference)

## Métodos
Requisições para a API devem seguir os padrões:

| Método | Descrição |
|---|---|
| `GET` | Retorna informações de um ou mais usuários. |
| `POST` | Utilizado para cadastrar um novo usuário.|
| `PUT` | Atualiza dados de um usuário.|

## Respostas

| Código | Descrição |
|---|---|
| `200` | Requisição executada com sucesso (success).|
| `201` | Registro cadastrado com sucesso. |
| `400` | Erros de validação ou os campos informados não existem no sistema.|
| `422` | Dados informados estão fora do escopo definido para o campo.|
| `429` | Número máximo de requisições atingido. (*aguarde alguns segundos e tente novamente*)|
| `500` | Erro Interno do Servidor. |

### Novo (Create) [POST /user]
+ Request (application/json)
    + Body
      
            {
                "username": "teste",
                "email": "teste@teste.com",
                "password": "xpto"
            }
    
+ Response 201 (application/json)
    + Body

            {
                "id": 1,
                "username": "teste",
                "email": "teste@teste.com",
                "password": "xpto"
            }

### Listar (List) [GET /user]
+ Parameters
    + page (optional, number, `1`) ... número da pagina desejada
    + page-size (optional, number, `5`) ... quantidade de registros por página
    
+ Response 200 (application/json)
    + Body

            [
                {
                    "id": 1,
                    "username": "teste",
                    "email": "teste@teste.com",
                    "password": "xpto"
                },
                {
                    "id": 2,
                    "username": "teste",
                    "email": "teste@teste.com",
                    "password": "xpto"
                },
                {
                    "id": 3,
                    "username": "teste",
                    "email": "teste@teste.com",
                    "password": "xpto"
                }
            ]

### Editar (Update) [PUT  /user/{id}]
+ Parameters
    + id (required, number, `1`) ... código do usuário

+ Request (application/json)
    + Body

            {
                "id": 1,
                "username": "teste atualizado",
                "email": "teste@teste.com",
                "password": "xpto"
            }

## Links
+ OpenAPI
    + Swagger
        + http://localhost:8080/swagger-ui/index.html
    
## Uso local
### Executando a aplicação
  + Pré-requisitos
      + <a href="https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html"> Java 11 </a>
  + Execute os seguintes comandos:
    + <code> mvn clean install </code>
    + <code> cd /target </code>
    + <code> java -jar users-0.0.1-SNAPSHOT.jar </code>
    
### Cobertura de testes (coverage)
+ Executar o comando abaixo para gerar o relatório de cobertura de testes:
    + <code> mvn clean install </code>
  
+ abrir <code> target/site/jacoco/index.html </code> para abrir o relatório.

##TODO List
+ Exception handler - mapear respostas de erro
+ Sonarqube
+ Docker
+ Deploy heroku
