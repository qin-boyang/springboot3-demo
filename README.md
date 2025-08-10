# Spring Boot Demo with Docker

A simple Spring Boot application containerized with Docker.

## Prerequisites

- Docker
- Docker Compose
- Java 24 (for local development)
- Maven (for building the application)

## Getting Started

### Build the Application

First, build your Spring Boot application:

```bash
mvn clean package
```


### Run with Docker Compose

Start the application using Docker Compose:

```bash
docker-compose up --build
```


The application will be available at http://localhost:8080

### Stop the Application

Stop and remove the containers:

```bash
docker-compose down
```


## Project Structure

- `src/` - Spring Boot application source code
- [Dockerfile](file:///Users/boyangqin/development/local-springboot-demo/Dockerfile) - Docker image configuration
- [compose.yaml](file:///Users/boyangqin/development/local-springboot-demo/compose.yaml) - Docker Compose configuration
- [pom.xml](file:///Users/boyangqin/development/local-springboot-demo/pom.xml) - Maven dependencies and build configuration

## Environment Variables

- `SPRING_PROFILES_ACTIVE` - Spring profile (default: docker)

## Ports

- 8080 - Application server port

## Notes

- Make sure to build the JAR file before running Docker Compose
- The JAR file should be located in the `target/` directory
- Modify the Dockerfile if your JAR file has a different name or location

## Application Walk Through

- docker-compose and Dockerfile (by defualt spring boot looks for compose.yaml/yml, docker-compose.yaml/yml)
- actuator endpoint `http://localhost:8080/actuator`
- logging to console 
- `schema.sql` and `data.sql` (automatically generated at startup)
- h2-console enabled and remote aaccess enabled (settings.web-allow-others)
- simple `api/products` and `api/inventory` endpoints created along with its CRUD operations
- spring boot modulith implementation with Unit Test. 3 modules created: `inventory`, `product`, `shared`.
- Event Publisher, Event Listener, Event Handler is for communication between modules, so modules are decoupled.