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

## 🚀 Como executar localmente

### Pré-requisitos
- **Java 21+**
- **Maven**
- **Docker**

### Passos

```bash
# Clonar o repositório
git clone https://github.com/BrunaCasagrande/solari-client-service.git
cd solari-client-service

# Executar localmente com Maven
mvn spring-boot:run