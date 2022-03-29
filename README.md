## Forum-API
Projeto de API REST desenvolvido com os conhecimentos aprendidos durante a Formação Spring da Alura.
## Descrição do Projeto
A API representa um fórum que possibilita, através de endpoints, o usuário se autenticar e 
interagir com tópicos de um fórum.

## O que aprendi
- Boas práticas do modelo REST
- Validar de campos no cadastro de Tópicos com Bean Validation e ExceptionHandler
- Implementar filtros, paginação e ordenação ao trazer a lista de Tópicos do banco
- Implementar cache em métodos Controller para melhorar a performance
- Realizar autenticação via JWT
- Limitar o acesso dos endpoints a certos perfis de usuário
- Monitorar a API com Actuator e Spring Boot Admin
- Documentar uma API REST com Swagger e Springfox
- Configurar ambientes de desenvolvimento (dev, test e prod)
- Criar testes automatizados
- Realizar build e Deploy
- Trabalhar com Docker

## O que utilizei
- Spring Boot
- Spring Data JPA para persistência de dados em banco de dados H2
- Spring Security com JWT (stateless)

## Métodos
&emsp; As requisições para a API devem seguir os padrões:

| Método HTTP | Sucesso     | Falha                           |                   
|:------------|-------------|---------------------------------|
| `GET`       | 200 OK      | 404 NOT_FOUND                   |
| `POST`      | 201 CREATED | 400 BAD_REQUEST                 |
| `PUT`       | 200 OK      | 400 BAD_REQUEST / 404 NOT_FOUND |
| `DELETE`    | 200 OK      | 400 BAD_REQUEST / 404 NOT_FOUND |

* Nessa API, apenas os métodos GET são públicos. Para o resto, é necessário o usuário enviar o Bearer Token para ter acesso aos endpoints. 

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
#### `GET`/topicos
* Retorna uma lista com todos os tópicos no banco de dados.
* Response:
  ```json
  {
    "id": "id do tópico",
    "titulo": "Título do Tópico",
    "mensagem": "Descrição do Tópico",
    "dataCriacao": "Data em que foi criado o Tópico"
  }
  ```

###  Detalhar Tópico
#### `GET`/topicos/{id}
* Busca no banco de dados o objeto com o id passado na URL.
* Response:
  ```json
    {
      "id": "id do tópico",
      "titulo": "Título do Tópico",
      "mensagem": "Descrição do Tópico",
      "dataCriacao": "Data em que foi criado o Tópico",
      "status": "Status do Tópico",
      "nomeAutor": "Autor do Tópico",
      "respostas": "Lista de Respostas associadas ao tópico"
    }
  ```

### Postar Tópico
#### `POST`/topicos/{id}
* Cria um novo registro de tópico no banco de dados.
* Request Body
  ```json
  {
    "titulo": "Problema X",
    "mensagem": "Estou com o problema X",
    "nomeCurso": "Spring Boot"
  }
  ```

### Atualizar Tópico
#### `PUT`/topicos/{id}
* Atualiza dados de um registro ou altera sua situação.
* Request Body
  ```json
  {
    "titulo": "Problema Y",
    "mensagem": "Estou com o problema Y"
  }
  ```
  
### Deletar Tópico
#### `DELETE`/topicos/{id}
* Remove um registro do sistema.