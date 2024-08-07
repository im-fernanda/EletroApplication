[JAVA_BADGE]:https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[BOOTSTRAP_BADGE]: https://img.shields.io/badge/bootstrap-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white

<h1 align="center" style="font-weight: bold;">EletroTADS 💻</h1>

![java][JAVA_BADGE]
![spring][SPRING_BADGE]
![bootstrap][BOOTSTRAP_BADGE]

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
  ```bash
  $ git clone im-fernanda/EletroApplication
  $ cd nome-do-repositiorio
  ```
3. Crie um arquivo application.propertier na raiz do projeto e insira suas credencias. Utilize como exemplo:
  ```yaml
  aws.region=us-east-1
  aws.accessKeyId={YOUR_AWS_KEY_ID}
  aws.secretKey={YOUR_AWS_SECRET}
  ```
5. Execute o projeto e abra localhost:8080;
6. Crie uma conta.;
7. Faça login.

<h2 id="routes">📍 API Endpoints</h2>

​
| route               | description                                          
|----------------------|-----------------------------------------------------
| <kbd>GET /index</kbd>     | acesso à homepage do site com acesso como cliente
| <kbd>GET /admin</kbd>     | acesso à homepage do site com acesso como gerente/admin
| <kbd>GET /carrinho</kbd>     | editar
| <kbd>POST /cadastrar</kbd>     | editar

