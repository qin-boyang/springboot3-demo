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
- Enabled virtual thread and no need for Reactive Programming Complexity

## How Java virtual thread improve spring boot rest api scalability

Java **virtual threads**, introduced in **Java 19 (JEP 425)** and stabilized in **Java 21**, significantly improve **Spring Boot REST API scalability** by reducing the overhead of thread-per-request concurrency models. Here’s how they enhance scalability:

### **1. Traditional Threads vs. Virtual Threads**
| Aspect            | Platform Threads (Old) | Virtual Threads (New) |
|-------------------|-----------------------|-----------------------|
| **OS Dependency** | 1:1 mapping to OS threads | Managed by JVM (M:N mapping) |
| **Memory Usage**  | ~1MB per thread (big stack) | Lightweight (~few KB) |
| **Blocking Cost** | Expensive (holds OS thread) | Cheap (yields thread) |
| **Max Concurrency** | Limited (~1k-10k threads) | Millions possible |

### **2. How Virtual Threads Improve Spring Boot Scalability**
#### **a) Higher Throughput with Fewer Resources**
- Virtual threads allow **handling millions of concurrent requests** with minimal memory overhead.
- Unlike platform threads, blocking operations (e.g., DB calls, HTTP requests) **do not block OS threads**, freeing them for other tasks.

#### **b) No Need for Reactive Programming Complexity**
- Traditionally, **Reactive (WebFlux)** was used for scalability but introduced complexity.
- Virtual threads allow **imperative (blocking) code** to scale similarly to reactive, simplifying development.

#### **c) Better Utilization of DB Connections & External Services**
- Since blocking calls (JDBC, HTTP clients) no longer hold OS threads, the system can **handle more concurrent I/O operations** efficiently.

#### **d) Seamless Integration with Spring Boot**
- **Spring Boot 3.2+** supports virtual threads out of the box.
- Just enable them in `application.properties`:
  ```properties
  spring.threads.virtual.enabled=true
  ```
- Works with **Tomcat, Jetty, and Undertow** (Tomcat 10.1+ recommended).

### **3. Benchmark Results (Example)**
| Scenario | Platform Threads (1000 threads) | Virtual Threads (1M threads) |
|----------|-------------------------------|-----------------------------|
| **Max Requests/sec** | ~5,000 | ~50,000+ |
| **Memory Usage** | High (~1GB) | Low (~100MB) |
| **Latency Under Load** | High (thread exhaustion) | Stable |

### **4. When to Use Virtual Threads?**
✅ **I/O-bound applications** (DB calls, HTTP APIs, file operations).  
✅ **Legacy Spring MVC apps** (no need to rewrite in WebFlux).  
✅ **High-concurrency microservices** (e.g., APIs handling thousands of requests).

❌ **Not ideal for CPU-bound tasks** (virtual threads don’t help with computation-heavy workloads).

### **5. How to Enable Virtual Threads in Spring Boot**
1. **Use Java 21+** (LTS).
2. **Add Spring Boot 3.2+** (or manually configure `Executor`).
3. **Enable virtual threads**:
   ```java
   @Bean
   public TomcatProtocolHandlerCustomizer<?> protocolHandlerVirtualThreadExecutorCustomizer() {
       return protocolHandler -> protocolHandler.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
   }
   ```
   Or in `application.properties`:
   ```properties
   spring.threads.virtual.enabled=true
   ```

### **6. Key Takeaways**
- Virtual threads **eliminate thread-pool bottlenecks** in Spring Boot.
- They **simplify scalable API development** without reactive programming.
- Best suited for **highly concurrent, I/O-bound workloads** (e.g., REST APIs, microservices).

