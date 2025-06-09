# Demo Spring Boot API

This project is a Kotlin-based Spring Boot application that demonstrates data validation, structured logging and database migrations with Flyway.

## Architecture Overview

- **Spring Boot 3 & Kotlin**: Main entry point is [`DemoApplication.kt`](src/main/kotlin/com/example/demo/DemoApplication.kt).
- **Persistence**: JPA/Hibernate with a PostgreSQL database. Database schema migrations are managed by Flyway. The initial migration creates the `examples` table at [`V1__create_examples_table.sql`](src/main/resources/db/migration/V1__create_examples_table.sql).
- **DTO & Validation**: `ExampleDto` uses validation groups (`OnCreate`, `OnUpdate`) to enforce different rules on create vs update requests.
- **Layers**:
  - [`ExampleController`](src/main/kotlin/com/example/demo/controller/ExampleController.kt) exposes CRUD endpoints under `/examples`.
  - [`ExampleService`](src/main/kotlin/com/example/demo/service/ExampleService.kt) contains business logic.
  - [`ExampleRepository`](src/main/kotlin/com/example/demo/repository/ExampleRepository.kt) extends `JpaRepository` for data access.
- **Directory Structure**: Example of how these components fit together

```text
src/main/kotlin/com/example/demo
├── controller
│   └── ExampleController.kt
├── dto
│   └── ExampleDto.kt
├── entity
│   └── ExampleEntity.kt
├── mapper
│   └── ExampleMapper.kt
├── repository
│   └── ExampleRepository.kt
└── service
    └── ExampleService.kt
```
- **Exception Handling**: [`GlobalExceptionHandler`](src/main/kotlin/com/example/demo/exception/GlobalExceptionHandler.kt) translates exceptions to HTTP responses.
- **Logging**: `Loggable` base class and [`StructuredLoggingJsonLayout`](src/main/kotlin/com/example/demo/logging/StructuredLoggingJsonLayout.kt) enable structured JSON logs.
- **API Docs**: [`SwaggerConfig`](src/main/kotlin/com/example/demo/config/SwaggerConfig.kt) sets up OpenAPI documentation.

Tests run using an in-memory H2 database (see [`src/test/resources/application.yaml`](src/test/resources/application.yaml)).

## Running Locally

### Full stack with Docker Compose

Run both the application and the PostgreSQL database as containers:

```bash
docker compose up
```

The API will then be available on `http://localhost:8080`.
Swagger docs are made available at the `/swagger-ui.html` endpoint

### Database container only

You can start only the database container and run the Spring Boot app via Gradle on your host:

```bash
# start postgres only
docker compose up db

# in another terminal, run the application
./gradlew bootRun
```

By default the app expects PostgreSQL to be accessible on `localhost:5432` with the username and password `demo`. These values can be overridden through environment variables defined in [`application.yaml`](src/main/resources/application.yaml).

## Running Tests

Execute the full test suite using the Gradle wrapper:

```bash
./gradlew test --no-daemon --console=plain
```

This will spin up an in-memory H2 database and apply Flyway migrations automatically.
