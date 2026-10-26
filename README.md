# SpringAI

A Java 21 and Spring Boot application exploring generative AI integration in the Spring ecosystem.

## Tech stack

- Java 21
- Spring Boot
- Spring AI
- Maven
- REST API development

## Getting started

### Prerequisites
- JDK 21
- Git
- Maven, or use the included Maven Wrapper

### Run locally

```bash
git clone https://github.com/Chakaliprakash/SpringAI.git
cd SpringAI
./mvnw spring-boot:run
```

On Windows PowerShell, run:

```powershell
.\mvnw.cmd spring-boot:run
```

## Configuration and secrets

Review the project's configuration files before starting the application. Set required API keys as environment variables locally; never commit real credentials, tokens, or populated secret files.

## Build and tests

```bash
./mvnw verify
```

If no tests are present yet, add tests for application services and controller behavior as the project evolves.

## Repository

[View source code](https://github.com/Chakaliprakash/SpringAI)
