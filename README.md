# Catalog Pricing Service

A backend service built with Kotlin and Spring Boot to simulate a product catalog and pricing system.

---

## Project Overview

Catalog Pricing Service is a backend application designed to manage products, pricing rules, and inventory-related operations.

This project was created as a portfolio backend project to demonstrate modern backend engineering practices, including:

- layered architecture
- REST API design
- database integration
- schema versioning with Flyway
- environment-based configuration
- local development with Docker
- clean and maintainable project structure

The goal is to evolve this project incrementally, starting with a strong technical foundation and later adding domain features such as product management, promotions, caching, testing, and CI/CD.

---

## Architecture

The project follows a layered architecture to keep responsibilities clear and make the codebase easier to evolve.

### Layers

- **API**  
  Handles HTTP requests and responses

- **Application**  
  Coordinates use cases and application flows

- **Domain**  
  Contains business rules and core models

- **Infrastructure**  
  Handles database access and technical integrations

- **Config**  
  Centralizes Spring Boot configuration

- **Shared**  
  Contains reusable components such as exceptions and utilities

### Dependency Flow
API -> Application -> Domain
Infrastructure -> Domain
Config -> supports framework wiring
Shared -> reusable across layers

---

## Technology Stack

- Kotlin
- Spring Boot
- Gradle (Kotlin DSL)
- PostgreSQL
- Flyway
- Docker / Docker Compose
- JUnit 5

---

## Project Goals

This project was created to showcase:

- clean architecture and separation of concerns
- REST API implementation with Kotlin and Spring Boot
- relational database integration using PostgreSQL
- database schema versioning with Flyway
- environment configuration with Spring profiles
- local development using Docker
- scalable backend structure

### Planned Future Improvements

- Product CRUD API
- Promotions and pricing engine
- Inventory management
- Redis caching
- Testcontainers integration
- GitHub Actions CI/CD
- Observability and metrics

---

## Repository Structure
src/main/kotlin/br/com/jema/catalog_pricing_service
├── api
├── application
├── domain
├── infrastructure
├── config
└── shared


### Resources
src/main/resources
├── application.yml
├── application-dev.yml
├── application-test.yml
└── db
└── migration


---

## Prerequisites

Before running the project, make sure you have installed:

- Java 21
- Docker
- Docker Compose
- Git

Gradle does not need to be installed manually because the project uses the Gradle Wrapper.

---

## How to Clone the Project

```bash
git clone https://github.com/jessicaetiene/jema-catalog-pricing-service.git
cd jema-catalog-pricing-service
```

## How to build the project
```bash
./gradlew build
```

## How to run docker
```bash
docker compose up -d
```

## How to run the application
```bash
SPRING_PROFILES_ACTIVE=dev ./gradlew bootRun
```

### The application will start at:
http://localhost:8080

## How to test the application
```bash
curl http://localhost:8080/health
```
