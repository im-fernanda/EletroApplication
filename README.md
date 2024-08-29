[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[BOOTSTRAP_BADGE]: https://img.shields.io/badge/bootstrap-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[THYMELEAF_BADGE]: https://img.shields.io/badge/thymeleaf-%236DB33F.svg?style=for-the-badge&logo=thymeleaf&logoColor=white

<h1 align="center" style="font-weight: bold;">EletroTADS 💻</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![bootstrap][BOOTSTRAP_BADGE]
![thymeleaf][THYMELEAF_BADGE]

<p align="center">
  <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> 
</p>

<p align="justify">
  <b>Este projeto foi desenvolvido como parte da disciplina de Programação Web  do curso de TADS da UFRN. O objetivo principal é demonstrar o uso do Spring Boot, juntamente com o Spring Security, utilizando Docker para a construção do contêiner do banco de dados e bootstrap como ferramenta auxiliar do front-end.</b>
</p>

<h2 id="started">🚀 Getting started</h2>

1. Crie um Banco de Dados no seu administrador de desenvolvimento para o PostgreSQL ou diretamente no contêiner Docker, criando assim o Banco de Dados inicial para testes. Com isso feito, siga os passos abaixo.
2. Clone o repositório e acesse a pasta do projeto:
  
       $ git clone im-fernanda/EletroApplication
       $ cd nome-do-repositiorio

3. Crie um arquivo application.propertier na raiz do projeto e insira suas credencias. Utilize como exemplo:
  ```yaml
  spring.datasource.url=jdbc:postgresql://localhost:5432/eletro_db
  spring.datasource.username=postgres
  spring.datasource.password=2005
  
  spring.datasource.driver-class-name=org.postgresql.Driver
  spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
  spring.jpa.hibernate.ddl-auto=update
  spring.servlet.multipart.enabled=true
  ```
5. Execute o projeto e abra localhost:8080;
6. Crie uma conta;
7. Faça login.

<h2 id="routes">📍 API Endpoints</h2>
​

| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /index</kbd>     | acesso à homepage do site com acesso como cliente
| <kbd>GET /admin</kbd>     | acesso à homepage do site com acesso como admin
| <kbd>POST /adicionarCarrinho/{id}</kbd>     | adiciona um produto ao carrinho, identificado pelo seu id
| <kbd>GET /verCarrinho</kbd>     | acesso ao carrinho com o cookie dos produtos com acesso como cliente
| <kbd>POST /finalizarCompra</kbd>     | fiinaliza a compra dos itens no carrinho, processando o pagamento e concluindo a transação
| <kbd>GET /displayCategoria/{categoria} </kbd>     | acesso à page de cadastro de produtos com acesso como admin
| <kbd>GET /produtoDetails/{id}</kbd> | acesso à página de detalhes de um produto específico, identificado pelo seu ID. Se o produto existir, seus detalhes são adicionados ao modelo e a página produtoDetails é exibida
| <kbd>POST /processSave/{editar_ou_cadastrar}</kbd> | processa o salvamento de um produto, verificando se é uma edição ou um novo cadastro, realiza validações e lida com upload de imagens.
| <kbd>GET /cadastroPage </kbd>     | acesso à page de cadastro de produtos com acesso como admin
| <kbd>GET /editPage/{id} </kbd>     | acesso à page de cadastro de produtos com acesso como admin
