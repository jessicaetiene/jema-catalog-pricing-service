# Architecture Overview

## Objective

This project follows a layered architecture to keep responsibilities clear and make the codebase easier to maintain, test, and evolve.

The main idea is to separate:

- HTTP concerns
- application flow orchestration
- business rules
- technical/infrastructure details
- shared cross-cutting concerns

---

## Layers

### 1. API

#### Responsibility
The API layer is responsible for handling HTTP communication.

It receives requests from clients, validates input, maps data to application use cases, and returns HTTP responses.

#### Typical contents
- Controllers
- Request DTOs
- Response DTOs
- HTTP-specific validation

#### What belongs here
- REST endpoints
- request/response models
- HTTP status handling
- path/query/body parsing

#### What should NOT belong here
- business rules
- database access
- persistence logic
- complex application orchestration

#### Examples in this project
- `HealthController`
- future `ProductController`
- future `PromotionController`

---

### 2. Application

#### Responsibility
The Application layer coordinates use cases and application flows.

It acts as an orchestration layer between the API and the Domain.  
It should call domain services, repositories, and other components required to complete a use case.

#### Typical contents
- Use cases
- Application services
- Command/query handlers
- Orchestration logic

#### What belongs here
- create product flow
- update inventory flow
- calculate final price flow
- transaction boundaries when needed

#### What should NOT belong here
- direct HTTP concerns
- controller annotations
- low-level persistence details
- framework-heavy code when avoidable

#### Examples in this project
- future `CreateProductUseCase`
- future `UpdateInventoryService`
- future `CalculateFinalPriceUseCase`

---

### 3. Domain

#### Responsibility
The Domain layer contains the core business rules of the system.

This is the most important layer of the application.  
It should represent the business concepts and rules in a way that is independent from frameworks and transport details.

#### Typical contents
- Entities
- Value objects
- Domain services
- Business rules
- Repository contracts/interfaces

#### What belongs here
- product rules
- promotion rules
- price calculation rules
- inventory rules
- domain contracts for persistence

#### What should NOT belong here
- controller logic
- HTTP annotations
- SQL queries
- JPA-specific concerns when avoidable
- framework-specific code unrelated to business rules

#### Examples in this project
- future `Product`
- future `Promotion`
- future `Inventory`
- future `ProductRepository` interface
- future pricing rule selection logic

---

### 4. Infrastructure

#### Responsibility
The Infrastructure layer implements technical details and integrations with external systems.

This layer supports the application by providing concrete implementations for persistence, cache, external APIs, messaging, and similar concerns.

#### Typical contents
- JPA entities if separated from domain
- Spring Data repositories
- Database adapters
- Redis cache adapters
- External API clients
- Messaging integration

#### What belongs here
- PostgreSQL integration
- Flyway database setup
- Redis cache integration
- repository implementations
- external service clients

#### What should NOT belong here
- HTTP controllers
- core business rules
- domain decisions that are independent of infrastructure

#### Examples in this project
- future Spring Data repository implementations
- future Redis cache configuration
- future persistence adapters

---

### 5. Config

#### Responsibility
The Config layer contains configuration classes required by the framework and the application runtime.

It centralizes technical setup and integration configuration.

#### Typical contents
- Spring configuration classes
- bean definitions
- cache configuration
- security configuration
- OpenAPI configuration
- Jackson configuration

#### What belongs here
- datasource config if needed
- Redis cache config
- OpenAPI/Swagger config
- custom bean definitions

#### What should NOT belong here
- business rules
- controller logic
- domain behavior

#### Examples in this project
- future `CacheConfig`
- future `OpenApiConfig`
- future `DatabaseConfig`

---

### 6. Shared

#### Responsibility
The Shared layer contains reusable components that are not specific to a single business module.

It should be used carefully to avoid becoming a "miscellaneous" folder.

#### Typical contents
- Exceptions
- Utility classes
- Common constants
- Base abstractions
- Reusable helpers

#### What belongs here
- common exceptions
- shared utilities
- reusable constants
- common response models if truly shared

#### What should NOT belong here
- unrelated business logic
- random code without clear ownership
- module-specific rules that belong elsewhere

#### Examples in this project
- future `ResourceNotFoundException`
- future `ValidationException`
- future utility helpers

---

## Dependency Rules

The project should follow these dependency directions:

```text
API -> Application -> Domain
Infrastructure -> Domain
Config -> supports framework/application wiring
Shared -> can be used by multiple layers when appropriate