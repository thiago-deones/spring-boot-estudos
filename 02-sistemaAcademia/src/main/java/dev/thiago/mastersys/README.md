# Academia API

API REST para gerenciamento de uma academia, desenvolvida com Java e Spring Boot como projeto de estudo e prática de desenvolvimento backend.

O projeto tem como objetivo aplicar conceitos de desenvolvimento de APIs REST, persistência de dados, validação, regras de negócio, consultas dinâmicas e documentação de APIs.

## 🚀 Tecnologias

- Java 26
- Spring Boot 4.1.1
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- Maven
- Bean Validation
- Swagger / OpenAPI
- Git e GitHub
- Postman
- IntelliJ IDEA

## 📚 Conceitos praticados

Durante o desenvolvimento do projeto são praticados conceitos como:

- Programação Orientada a Objetos
- APIs REST
- Arquitetura em camadas
- Injeção de dependências
- DTOs
- Validação de dados
- Regras de negócio
- Tratamento global de exceções
- Persistência com JPA/Hibernate
- Consultas com Spring Data JPA
- Specifications para filtros dinâmicos
- Paginação
- Migração e versionamento de banco de dados com Flyway
- Documentação de API com OpenAPI/Swagger
- Git e GitHub

## 🏗️ Estrutura

O projeto utiliza uma organização baseada em responsabilidades:

```text
src/main/java/dev/thiago/mastersys/

├── config/
│   └── OpenApiConfig.java
│
├── controller/
│   └── AlunoController.java
│
├── doc/
│   └── AlunoControllerDoc.java
│
├── domain/
│   └── Aluno.java
│
├── dto/
│   ├── AlunoFiltroRequest.java
│   ├── AlunoRequest.java
│   └── AlunoResponse.java
│
├── exception/
│   ├── ErroResponse.java
│   ├── GlobalExceptionHandler.java
│   └── RegraNegocioException.java
│
├── repository/
│   └── AlunoRepository.java
│
├── service/
│   └── AlunoService.java
│
└── specification/
    └── AlunoSpecification.java