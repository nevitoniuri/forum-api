## Forum-API
Projeto de API REST desenvolvido com os conceitos aprendidos durante a Formação Spring da Alura.
## Descrição do Projeto
A API representa um fórum, sendo possível, através de endpoints, o usuário se autenticar e 
interagir com os tópicos (CRUD).

## O que aprendi
- Boas práticas do modelo REST
- Validação de campos no cadastro de Tópicos com Bean Validation e ExceptionHandler
- Filtros, Paginação e ordenação ao trazer a lista de Tópicos do banco
- Implementar Cache em métodos Controller para melhorar a performance
- Realizar autenticação via JWT para controle de acesso aos endpoints
- Utilizar o formulário de Autenticação do Spring
- Monitoramento com Actuator

## O que utilizei
- Spring Boot
- Spring Data JPA para Persistencia de dados em banco de dados H2
- Spring Security com JWT (stateless)

## Documentação

## Métodos
&emsp; As requisições para a API devem seguir os padrões:

| Método HTTP | Sucesso        | Erro                                  |                   
|:------------|----------------|---------------------------------------|
| `GET`       | 200 OK         | 404 NOT_FOUND                         |
| `POST`      | 201 CREATED    | 400 BAD_REQUEST                       |
| `PUT`       | 200 OK         | 400 BAD_REQUEST / 404 NOT_FOUND       |
| `DELETE`    | 204 NO_CONTENT | 400 BAD_REQUEST / 404 NOT_FOUND       |


### Autenticar Usuário
#### `POST` /auth
* Request Body
  ```json
  {
    "email": "aluno@email.com",
    "senha": "123456"
  }
  ```

###  Listar Tópicos
####`GET`/topicos
    Retorna uma lista com todos os tópicos no banco de dados.

###  Detalhar Tópico
####`GET`/topicos/{id}
    Busca no banco de dados o objeto com o id passado na URL.

### Postar Tópico
####`POST`/topicos/{id}
    Cadastra um novo tópico no banco de dados.

Request Body:
  ```json
  {
    "titulo": "Problema X",
    "mensagem": "Estou com o problema X",
    "nomeCurso": "Spring Boot"
  }
  ```
