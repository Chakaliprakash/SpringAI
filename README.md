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

## Overview

SpringAI is a Java 21 and Spring Boot application that exposes REST endpoints for experimenting with multiple generative AI providers.

## Features

- Chat endpoints for OpenAI, Gemini, and Ollama.
- Spring AI `ChatClient` integration.
- Prompt-based text responses and structured response handling.
- Maven-based build.

## Technology Stack

- Java 21
- Spring Boot 4
- Spring AI
- Maven

## Run Locally

Set the required provider credentials in a local `.env` file or environment variables. Ollama also requires a local Ollama service and the configured model.

```bash
git clone https://github.com/Chakaliprakash/SpringAI.git
cd SpringAI
./mvnw spring-boot:run
```

On Windows, run `mvnw.cmd spring-boot:run`. The application is configured for port `8081`.

## API Examples

Replace `your question` with a URL-encoded prompt.

- `GET /chat/openai?message=your%20question`
- `GET /chat/gemini?message=your%20question`
- `GET /chat/ollama?message=your%20question`

Check `ChatController` for additional endpoints and current response behavior.

## Configuration

Provider keys are read from configuration and environment variables. Never commit real API keys. Cloud-provider credentials are independent; Ollama runs locally.

## Build and Test

```bash
./mvnw verify
```

## Author

[Prakash Chakali](https://github.com/Chakaliprakash)
