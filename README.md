# Lode Manutenções

Sistema de gestão de equipamentos de poços artesianos. Permite o cadastro, listagem, edição e remoção de equipamentos, com filtro de busca em tempo real.

## Stack

- **Backend:** Java 17 + Spring Boot 4 (Spring Web MVC, Spring Data JPA, Bean Validation)
- **Frontend:** Vue 3 + Vite + Axios
- **Banco de dados:** PostgreSQL
- **Testes:** JUnit 5 + Mockito

## Pré-requisitos

- Java 17+
- Node.js 18+
- PostgreSQL 14+

## Como executar

### 1. Banco de dados

Crie o banco no PostgreSQL:

```sql
CREATE DATABASE lode;
```

Ajuste as credenciais em `backend/src/main/resources/application.properties` se necessário:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/lode
spring.datasource.username=postgres
spring.datasource.password=123456
```

### 2. Backend

```bash
cd backend
./mvnw spring-boot:run
```

API disponível em `http://localhost:8080/api/equipamentos`.

Para rodar os testes:

```bash
./mvnw test
```

### 3. Frontend

```bash
cd frontend
npm install
npm run dev
```

Aplicação disponível em `http://localhost:5173`.
