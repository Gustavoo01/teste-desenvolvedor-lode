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

## Respostas — Avaliação de Perfil

### 1. Análise: Ao receber um bug report informando que "os dados não aparecem na tela",qual seria sua sequência lógica de investigação (do frontend ao banco)?

Primeira coisa que faço é abrir o DevTools na aba Network pra ver se a chamada está saindo. Se a requisição volta vazia mas com status 200, o problema está no banco ou na query do backend. Se vem erro, olho a resposta e os logs do Spring. Se nem sai requisição, é coisa do front mesmo, erro de render ou URL errada. Se chegar até o banco, dou um SELECT direto pra confirmar se os dados existem.

### 2. Trabalho em Equipe: Você finalizou sua tarefa antes do prazo, mas um colega de equipe está atrasado com uma entrega crítica para o deploy de amanhã. O que você faz?

Pergunto pra ele em que parte está travado e ofereço ajuda no que faça sentido eu pegar. Antes alinho com quem coordena pra não atropelar nada. Acho que entregar antes do prazo não significa muito se o time fica pra trás no dia seguinte, então prefiro ajudar.

### 3. Git: Descreva o fluxo de comandos Git que você utilizaria para criar uma nova funcionalidade e enviá-la para o repositório principal, garantindo que não haja conflitos com o trabalho dos outros.

Crio uma branch a partir da main com `git checkout -b feature/nome-da-feature`. Vou commitando aos poucos enquanto desenvolvo. Antes de abrir o PR puxo as alterações da main com `git pull --rebase origin main` pra resolver conflito local se tiver, depois `git push` e abro o Pull Request pra revisão.