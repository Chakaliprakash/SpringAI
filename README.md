<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&height=230&color=0:052e16,35:166534,70:15803d,100:4ade80&text=SpringAI&fontColor=ffffff&fontSize=54&fontAlignY=38&desc=Building%20with%20Java%2021%20%7C%20Spring%20Boot%20%7C%20Spring%20AI&descAlignY=62&descSize=16" width="100%" alt="SpringAI green technology banner"/>

<br/>

<a href="https://www.oracle.com/java/"><img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21"/></a>
<a href="https://spring.io/projects/spring-boot"><img src="https://img.shields.io/badge/Spring%20Boot-Application-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot"/></a>
<a href="https://spring.io/projects/spring-ai"><img src="https://img.shields.io/badge/Spring%20AI-Generative%20AI-2E7D32?style=for-the-badge" alt="Spring AI"/></a>
<img src="https://img.shields.io/badge/Build-Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>

<br/><br/>

**A Java 21 playground for exploring generative AI integration in the Spring ecosystem.**

[Overview](#-project-overview) · [Tech Stack](#-tech-stack) · [Run Locally](#-get-started) · [Configuration](#-configuration) · [Build & Test](#-build--test)

</div>

---

## Project Overview

SpringAI is a Java 21 and Spring Boot application for exploring generative AI integration with the Spring ecosystem.

## Highlights

- Java and Spring Boot application structure.
- Spring AI integration for AI-powered features.
- Maven-based dependency management and builds.
- Environment-based configuration for sensitive credentials.

## Tech Stack

| Technology | Purpose |
|:--|:--|
| Java 21 | Application language |
| Spring Boot | Backend framework |
| Spring AI | AI integration |
| Maven | Build and dependency management |

## Run Locally

Clone the repository:

```bash
git clone https://github.com/Chakaliprakash/SpringAI.git
cd SpringAI
```

Run with the Maven Wrapper:

```bash
./mvnw spring-boot:run
```

On Windows, use `mvnw.cmd spring-boot:run`.

## Configuration

Set the API keys required by your selected model provider as environment variables. Never commit real credentials to GitHub.

## Build and Test

```bash
./mvnw verify
```

## Author

[Prakash Chakali](https://github.com/Chakaliprakash)
