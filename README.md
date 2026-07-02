# SDET Quality Automation Framework

[![Automation Tests](https://github.com/FarhadSadat224/sdet-quality-automation-framework/actions/workflows/tests.yml/badge.svg)](https://github.com/FarhadSadat224/sdet-quality-automation-framework/actions/workflows/tests.yml)

A clean Java API-automation portfolio project built to show production-minded test design: deterministic execution, reusable configuration, readable assertions, and CI feedback on every pull request.

## Why this project stands out

- Tests run against a local WireMock service—no flaky third-party dependency.
- REST Assured validates status, headers, JSON fields, and request behavior.
- JUnit 5 provides expressive test structure and lifecycle management.
- Jackson uses typed immutable records instead of hand-built JSON strings.
- GitHub Actions runs the suite and preserves test reports automatically.
- Environment settings support local and CI execution through Maven properties.

## Tech stack

Java 17 · Maven · REST Assured · JUnit 5 · WireMock · Jackson · GitHub Actions

## Project structure

```text
src/
├── main/java/com/farhadsadat/qa/
│   ├── client/        # API domain models
│   └── config/        # Environment-aware configuration
└── test/
    ├── java/.../api/  # API contract and behavior tests
    └── resources/     # Environment properties
```

## Run locally

Prerequisites: Java 17+ and Maven 3.9+.

```bash
mvn clean test
```

Override a setting without changing source files:

```bash
mvn test -Drequest.timeout.ms=8000
```

Test reports are generated in `target/surefire-reports/`.

## Test strategy demonstrated

| Layer | What is validated | Why |
|---|---|---|
| HTTP contract | Status and content type | Detect integration breaks quickly |
| Response schema | Required values and types | Protect consumer expectations |
| Request behavior | Path and correlation header | Verify the client sends the right request |
| Reliability | Local deterministic stub | Keep CI fast and repeatable |

## Next enhancements

- Add negative and schema-validation scenarios
- Add a Selenium Page Object module for UI coverage
- Publish Allure reports from CI
- Add Docker-based parallel test execution

## Author

**Sayed Farhad Sadat** — SDET / Quality Automation Engineer, open to remote opportunities.

[LinkedIn](https://www.linkedin.com/in/sayed-farahd-sadat-5b9ab3286/) · [GitHub](https://github.com/FarhadSadat224)

