# Avenga Bookstore API Automation

## Overview

This project contains automated API tests for a demo Bookstore REST API using Java, RestAssured, and TestNG.
The goal is to demonstrate clean test automation architecture, reusable components, and coverage of both positive and
negative scenarios. The project follows a layered architecture with separation between test logic,
API clients, and test data generation.

Base API:
https://fakerestapi.azurewebsites.net
---

## Tech Stack

- Java 21
- Maven
- RestAssured
- TestNG
- Allure Reports
- Lombok
- AssertJ

---

## 📁 Project Structure

src/test/java/com/bojidar/avenga/bookstore
│
├── base - Base test setup (RestAssured config)
├── clients - API interaction layer
├── factories - Test data generation
├── models - POJOs for Books and Authors
├── tests - Test cases
├── utils - Config and helpers
---

## Setup & Run

### 1. Clone the repository

git clone <repo-url> ТУКА /* гит */ ### ffffff
cd bookstore-api-automation

### 2. Run tests

mvn clean test
---

## Allure Reporting

Generate and open report:
allure serve target/allure-results
---

## Test Coverage

### Books API

| Endpoint           | Scenario           | Status |
|--------------------|--------------------|--------|
| GET /Books         | Retrieve all books | ✅      |
| GET /Books/{id}    | Valid ID           | ✅      |
| GET /Books/{id}    | Invalid ID         | ✅      |
| POST /Books        | Valid payload      | ✅      |
| POST /Books        | Invalid payloads   | ✅      |
| PUT /Books/{id}    | Update book        | ✅      |
| PUT /Books/{id}    | Invalid ID         | ✅      |
| DELETE /Books/{id} | Delete book        | ✅      |

### Authors API

| Endpoint             | Scenario             | Status |
|----------------------|----------------------|--------|
| GET /Authors         | Retrieve all authors | ✅      |
| GET /Authors/{id}    | Valid ID             | ✅      |
| GET /Authors/{id}    | Invalid ID           | ✅      |
| POST /Authors        | Valid payload        | ✅      |
| POST /Authors        | Invalid payloads     | ✅      |
| PUT /Authors/{id}    | Update author        | ✅      |
| PUT /Authors/{id}    | Invalid ID           | ✅      |
| DELETE /Authors/{id} | Delete author        | ✅      |

---

## Notes

- The API used is a **demo API (FakeRestAPI)** and does not persist data.
- Some endpoints (e.g., DELETE, invalid payloads) may return inconsistent responses.
- Tests are designed to handle this behavior appropriately.

---

## Design Decisions

- Client layer separates API calls from test logic.
- Factory pattern is used for test data generation.
- BaseTest centralizes RestAssured configuration.
- Models are used for request and response serialization.
- Allure integration provides detailed test reporting.
- AssertJ is used for readable assertions.
- Test data is generated in a controlled way to avoid unnecessary randomness and flaky tests.

---

## Possible Improvements

- Schema validation
- Contract testing
- More boundary value tests
- Parameterized tests
- Docker execution
- Kubernetes job execution
- CI/CD pipeline integration
- Retry mechanism for unstable demo API responses

---

## Author

Bojidar