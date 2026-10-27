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

## ✦ Project overview

SpringAI is a Spring Boot application for experimenting with AI-powered application development using the Spring ecosystem. The project provides a place to configure the application, connect AI capabilities, and evolve the implementation through small, testable increments.

<table>
<tr>
<td width="50%" valign="top">

### ⚙️ Spring-native

Use familiar Java and Spring Boot conventions to structure the application and expose backend functionality.

</td>
<td width="50%" valign="top">

### ✨ AI integration

Explore generative AI features through the Spring AI ecosystem and its model/client abstractions.

</td>
</tr>
<tr>
<td width="50%" valign="top">

### 🔐 Configuration-aware

Keep API credentials outside source control and provide them through local environment variables.

</td>
<td width="50%" valign="top">

### 🧪 Developer workflow

Build and verify the project with Maven or the included Maven Wrapper.

</td>
</tr>
</table>

## 🧭 How the pieces fit

<div align="center">

<table>
<tr>
<td align="center"><b>Client / API request</b></td>
<td align="center">→</td>
<td align="center"><b>Spring Boot app</b></td>
<td align="center">→</td>
<td align="center"><b>Spring AI integration</b></td>
<td align="center">→</td>
<td align="center"><b>Configured model</b></td>
</tr>
</table>

<sub>Conceptual flow — the exact endpoints and model providers depend on the current application configuration.</sub>

</div>

## 🧰 Tech stack

<div align="center">

| Technology | Purpose |
|:--|:--|
| **Java 21** | Main programming language |
| **Spring Boot** | Application framework |
| **Spring AI** | AI integration layer |
| **Maven** | Dependency management and build |

</div>

## 🚀 Get started

### Prerequisites

- JDK 21
- Git
- Maven, or use the included Maven Wrapper

### 1. Clone the repository

```bash
git clone https://github.com/Chakaliprakash/SpringAI.git
cd SpringAI
```

### 2. Run the application

**Windows PowerShell**

```powershell
.\mvnw.cmd spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

## 🔐 Configuration

Review the project's configuration files before starting the application. Set any required API keys as environment variables on your machine.

- Never commit real API keys, access tokens, or populated secret files.
- Keep local credentials in environment variables or a local, ignored configuration file.
- Check the application logs if startup fails due to missing configuration.

## 🧪 Build & test

Run the Maven verification lifecycle:

```bash
./mvnw verify
```

On Windows:

```powershell
.\mvnw.cmd verify
```

As the project evolves, add tests for application services, configuration, and controller behavior.

---

<div align="center">

### Build. Integrate. Experiment. Improve.

[**View source code on GitHub →**](https://github.com/Chakaliprakash/SpringAI)

<sub>Maintained by <a href="https://github.com/Chakaliprakash">Prakash Chakali</a></sub>

</div>
