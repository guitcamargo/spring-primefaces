# spring-primefaces
Projeto utilizando Java com Spring boot + JSF (Primefaces)
================

Esse é um projeto desenvolvido para a solução do teste abaixo:

Desenvolva uma aplicação Java com JSF + richfaces (ou primefaces). Esta aplicação listará os planetas encontrados em Star Wars.

Funcionalidades desejadas: 
- Adicionar um planeta (com nome, clima e terreno)
- Listar planetas
- Buscar por nome
- Buscar por ID
- Remover planeta

Requisitos:
- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente: Nome, Clima e Terreno.
- Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas
pela API pública do Star Wars: https://swapi.dev/about, buscando pelo nome do planeta 

Linguagens: Java

Bancos: SQL em memória
Um bom software é um software bem testado.

Projeto Desenvolvido
================
Foi desenvolvido um projeto utilizando a linguagem Java(11) com spring boot.
Projeto contendo uma tela composta por componentes do primefaces onde listará todos os planetas cadastrados e também um formulário para cadastro de um novo planeta.
O projeto também contempla um RESTfull com hateoas para o domínio planeta contendo as mesmas funcionalidades liberadas para o frontend, sendo elas:
- Criação de um planeta (POST - /planetas) {nome, clima, terreno}
- Busca de um planeta(GET - /planetas/{idPlaneta}
- Busca de vários planetas através de um filtro(GET - /planetas{matrixVariables})
  - nome
  - clima
  - terreno
- Remoção de um planeta (DELETE - /planetas/{idPlaneta}

Criação de teste unitário para os recursos rest.


O que faltou:
================
Melhorar:
  - Tela -> Por falta de tempo, acabei deixando as funcionalidades na mesma tela, o que eu melhoraria? Criaria uma tela .index.xhtml com 2 opções, cadastro / listagem.
    - criação de uma tela isolada para cadastro.
    - criação de uma tela para index.
  - Criação do dockerfile para nao precisar executar o projeto/baixar dependencias. (falta de tempo)
  
  
Fiz tudo em um total de 6h dividido entre 4 dias.


Frameworks e Tecnologias Utilizadas
================
* [JSF](https://javaserverfaces.java.net/)
* [Primefaces](http://www.primefaces.org/)
* [JPA](http://www.oracle.com/technetwork/java/javaee/tech/persistence-jsp-140049.html)
* [Hibernate](http://hibernate.org/)
* [Maven](https://maven.apache.org/)
* [H2](https://www.h2database.com/)
* [JUnit](https://docs.spring.io/spring-framework/docs/current/reference/html/testing.html)
* [Hateoas]
* [spring-validation]
* [JUnit - Spring-test]

Como Baixar Este Projeto
================

```sh
git clone https://github.com/guitcamargo/spring-primefaces.git
```

Como executar o projeto
================

Acesse a raiz do projeto e execute o comando:

```sh
mvn spring-boot:run
```

Após startar o projeto, acesso: http://localhost:8080/planetas.xhtml ou consumir os recursos citados acima.

Como executar os testes
================

Acesse a raiz do projeto e execute o comando:

```sh 
mvn clean test surefire-report:report-only
```

Banco de dados
================

O banco de dados é criado automaticamente pelo Hibernate e populado através de uma configuração do .properties do projeto. 
Caso julgue necessário poderá remover o carregamento de dados para o banco apenas trocando a configuração:

load-db-datas=false
