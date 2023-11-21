# 🚀 Task Management System (Microservices)

This is an example project of a task management system built with Spring Boot, following the microservices architecture. The main goal is to provide a foundation for developers who want to understand how to build and deploy a distributed application using Spring Boot.

## Prerequisites

Make sure you have the following tools installed on your machine:

- Java 11 or higher
- Maven
- Docker
- Docker Compose

## Configuration and Execution

1. Clone the repository:

    ```bash
    git clone https://github.com/erickbarbosa/task-management-system.git
    ```

2. Navigate to the project directory:

    ```bash
    cd task-management-system
    ```

3. Run the microservices using Docker Compose:

    ```bash
    docker-compose up -d
    ```

4. Wait until all containers are up and running.

5. Access the 🚦 [Eureka Dashboard](http://localhost:8761) to check registered services.

6. Access 📚 [Swagger UI](http://localhost:8080/swagger-ui.html) to explore the API.

## Microservices

The application consists of the following microservices:

- **Task Service**: Manages tasks, allowing the creation, updating, and deletion of tasks.

- **User Service**: Manages users, responsible for authentication and authorization.

- **Gateway Service**: Routes requests to the appropriate services and provides a security layer.

## Testing

To run unit tests, use the following command:

    ```bash
    mvn test
    ```

Integration tests can be executed as follows:

    ```bash
    mvn verify
    ```

## Architecture

The architecture follows the microservices pattern, providing scalability and flexibility. It uses the 🌐 Eureka Server for service registration and discovery and the 🌐 Gateway Service for request routing.

Communication between microservices is done synchronously via HTTP, and security is implemented using JWT (JSON Web Token).

## Contributing

Feel free to contribute and improve this project. Open an issue to discuss new features or bug fixes, and submit pull requests to contribute code.

## License

This project is licensed under the [MIT License](LICENSE).
