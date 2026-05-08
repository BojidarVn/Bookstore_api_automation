# Avenga Bookstore API Automation

![CI](https://github.com/BojidarVn/Bookstore_api_automation/actions/workflows/api-tests.yml/badge.svg)

## Overview

This project contains automated API tests for a demo Bookstore REST API using Java, RestAssured, and TestNG.

The goal of the project is to demonstrate:

- clean automation architecture
- reusable API components
- scalable test structure
- CI/CD integration
- Docker and Kubernetes execution
- advanced reporting with Allure

The framework covers both positive and negative API scenarios and follows a layered architecture with clear separation
between:

- test logic
- API clients
- models
- test data generation
- configuration utilities

---

## Base API

https://fakerestapi.azurewebsites.net/

---

## Tech Stack

- Java 21
- Maven
- RestAssured
- JSON Schema Validator
- TestNG
- Allure Reports
- Docker
- Kubernetes
- GitHub Actions
- Lombok
- AssertJ

---

## Project Structure

```text
.
├── .github/workflows
│   └── api-tests.yml              -> GitHub Actions CI pipeline
├── k8s
│   └── api-tests-job.yaml         -> Kubernetes Job configuration
├── src/test/java/com/bojidar/avenga/bookstore
│   ├── base                       -> Shared RestAssured test setup
│   ├── clients                    -> Reusable API client layer
│   ├── factories                  -> Test data builders
│   ├── models                     -> Request/response POJOs
│   ├── tests                      -> TestNG API test classes
│   └── utils                      -> Configuration utilities
├── src/test/resources
│   ├── schemas                    -> JSON schema files
│   ├── testng.xml                 -> TestNG suite configuration
│   ├── allure.properties          -> Allure configuration
│   └── environment.properties     -> Report environment metadata
├── Dockerfile                     -> Docker image for running the tests
├── pom.xml                        -> Maven project configuration
├── README.md                      -> Project documentation
├── run-docker-tests-and-report.sh -> Docker test execution script
└── run-k8s-tests.sh               -> Kubernetes test execution script
```

---

## Architecture Principles

- Layered architecture separates test logic from API communication
- Reusable client classes provide centralized endpoint interaction
- POJO models support request serialization and response deserialization
- Factory classes centralize reusable test data generation
- Base test configuration keeps RestAssured setup consistent across tests
- Tests are isolated and suitable for local, Docker, and CI/CD execution
- Utility classes centralize shared configuration and reduce duplication

---

## Clone Repository

```bash
git clone https://github.com/BojidarVn/Bookstore_api_automation.git
cd Bookstore_api_automation
```

---

## Run Tests Locally

```bash
mvn clean test
```

---

## Generate Allure Report

```bash
allure serve target/allure-results
```

---

## Reporting

Allure reporting provides:

- test execution summary
- pass/fail status per test case
- request and response visibility
- failed test diagnostics
- stack traces for troubleshooting
- execution timeline visibility
- test categorization through epic, feature, story, and severity annotations

---

## Run Tests with Docker

```bash
./run-docker-tests-and-report.sh
```

This script:

- builds the Docker image
- runs tests inside container
- generates Allure report
- opens the report automatically

---

## Run Tests with Kubernetes

```bash
./run-k8s-tests.sh
```

This script:

- builds the Docker image
- deploys Kubernetes Job
- waits for pod completion
- prints test execution logs

Kubernetes execution demonstrates how automated API tests can run inside a containerized orchestration environment similar to real CI/CD infrastructure.

---

## GitHub Actions CI/CD

The project includes automated CI execution using GitHub Actions.

Pipeline includes:

- Maven build
- API test execution
- Allure results generation
- Allure HTML artifact upload
- Surefire report upload

Artifacts can be downloaded from:

```text
GitHub → Actions → Latest Workflow Run → Artifacts
```

---

## Test Coverage

Coverage includes:

- happy path CRUD scenarios
- edge cases for invalid IDs and boundary-like payload values
- status code validation
- response body validation
- JSON schema validation for core response contracts
- request serialization and response deserialization using POJO models
- verification of actual FakeRestAPI behavior, including non-persistent write operations

### Books API

| Endpoint           | Scenario                                             | Status |
|--------------------|------------------------------------------------------|--------|
| GET /Books         | Retrieve all books                                   | ✅      |
| GET /Books/{id}    | Valid ID                                             | ✅      |
| GET /Books/{id}    | Invalid ID returns 404                               | ✅      |
| POST /Books        | Valid payload                                        | ✅      |
| POST /Books        | Empty title accepted as current API behavior         | ✅      |
| POST /Books        | Negative page count accepted as current API behavior | ✅      |
| POST /Books        | Invalid publish date rejected                        | ✅      |
| PUT /Books/{id}    | Valid ID update                                      | ✅      |
| PUT /Books/{id}    | Non-existing ID accepted as current API behavior     | ✅      |
| DELETE /Books/{id} | Valid ID delete                                      | ✅      |
| DELETE /Books/{id} | Non-existing ID accepted as current API behavior     | ✅      |

---

### Authors API

| Endpoint             | Scenario                                          | Status |
|----------------------|---------------------------------------------------|--------|
| GET /Authors         | Retrieve all authors                              | ✅      |
| GET /Authors/{id}    | Valid ID                                          | ✅      |
| GET /Authors/{id}    | Invalid ID returns 404                            | ✅      |
| POST /Authors        | Valid payload                                     | ✅      |
| POST /Authors        | Empty first name accepted as current API behavior | ✅      |
| POST /Authors        | Empty last name accepted as current API behavior  | ✅      |
| PUT /Authors/{id}    | Valid ID update                                   | ✅      |
| PUT /Authors/{id}    | Non-existing ID accepted as current API behavior  | ✅      |
| DELETE /Authors/{id} | Valid ID delete                                   | ✅      |
| DELETE /Authors/{id} | Non-existing ID accepted as current API behavior  | ✅      |

---

## Design Decisions

- Client layer separates API communication from test logic
- Factory pattern is used for test data generation
- BaseTest centralizes RestAssured configuration
- POJO models are used for request/response serialization
- AssertJ provides readable assertions
- Allure provides detailed reporting and execution visibility
- Docker enables consistent execution environment
- Kubernetes Job execution simulates container orchestration workflow
- GitHub Actions provides CI/CD automation
- JSON Schema validation is used for selected core response contracts
- Edge-case tests assert documented FakeRestAPI behavior rather than unsupported business rules

---

## Notes

- The project uses a public demo API (FakeRestAPI).
- Create, update, and delete operations return simulated responses and do not reliably persist data.
- Some endpoints do not enforce strict server-side validation for empty or boundary-like field values.
- Invalid publish date payloads are rejected by the API and validated through dedicated edge-case scenarios.
- Edge-case tests document the actual FakeRestAPI behavior instead of assuming stricter business validation than the service provides.
- Tests are designed to remain stable, independent, and suitable for repeated local, Docker, Kubernetes, and CI execution.
- Some transitive dependency vulnerability warnings originate from third-party testing libraries used for JSON schema validation.

---

## Possible Improvements

- Full response contract coverage for all endpoints
- Reusable request and response specifications for standardized validation
- Centralized reusable schema validation utilities
- Parallel test execution
- Environment-specific configuration profiles
- Retry mechanism for transient public API or network failures
- Docker Compose support for local containerized test execution
- Docker Hub image publishing
- Automated Kubernetes resource cleanup after test job execution
- GitHub Pages Allure report publishing
- Extended Allure reporting with historical trend visibility

---

## Author

Bozhidar Baltadzhiev
