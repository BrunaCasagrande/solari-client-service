# Solari - Client Service

Este microsserviço é responsável por gerenciar os clientes do sistema, incluindo operações de criação, consulta, atualização e exclusão de clientes. Ele faz parte do sistema de gerenciamento de pedidos do projeto **Solari**, desenvolvido no **Tech Challenge - Fase 4** da pós-graduação em Arquitetura e Desenvolvimento Java - FIAP.

---

## 🧩 Tecnologias Utilizadas

- **Java 21**: Linguagem principal do projeto.
- **Spring Boot**: Framework para criação de aplicações Java.
- **Maven**: Gerenciador de dependências e build.
- **Flyway**: Controle de versão do banco de dados.
- **JPA / Hibernate**: Persistência de dados.
- **Docker**: Containerização da aplicação.
- **JaCoCo**: Ferramenta para análise de cobertura de testes.
- **JUnit 5 + Mockito**: Frameworks para testes unitários e mocks.

---

## 🧱 Estrutura do Projeto

O projeto segue a arquitetura hexagonal, dividindo responsabilidades em camadas bem definidas:

- **application**: Contém os casos de uso e regras de negócio.
- **domain**: Representa as entidades e objetos de domínio.
- **infrastructure**: Implementações de gateways, repositórios, controladores e configurações.
- **tests**: Testes unitários e de integração.

---

## 🚀 Como executar localmente

### Pré-requisitos
- Java 21+
- Maven
- Docker

### Passos
1. Clonar o repositório:
- git clone https://github.com/BrunaCasagrande/solari-client-service.git
- cd solari-client-service

2. Executar o projeto com Maven:
- mvn spring-boot:run

---

## 📌 Endpoints Principais

### Clientes

- **POST** `/solari/v1/clients`  
  Cria um novo cliente.

- **GET** `/solari/v1/clients/{cpf}`  
  Busca um cliente pelo CPF.

- **PUT** `/solari/v1/clients/{cpf}`  
  Atualiza os dados de um cliente.

- **DELETE** `/solari/v1/clients/{cpf}`  
  Remove um cliente pelo CPF.

---

## ✅ Testes

Para executar os testes e gerar o relatório de cobertura com JaCoCo:

1. Executar os testes: mvn test
2. Gerar o relatório de cobertura: mvn jacoco:report
3. Acessar o relatório em `file:///C:/solari/solari-client-service/target/site/jacoco/index.html`

---

## 🐳 Executando com Docker

### Build da imagem Docker:

docker build -t solari-client-service .

### Executar o container:
docker run -p 8080:8080 solari-client-service

### Acessar a aplicação:
http://localhost:8080/solari/v1/clients

---

## 📚 Documentação da API

A documentação da API pode ser acessada em:

http://localhost:8080/swagger-ui/index.html

---

## 👩‍💻 Autor

Desenvolvido por **Bruna Casagrande** como parte do projeto **Solari**.
RM: 359536