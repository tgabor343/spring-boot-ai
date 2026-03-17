# Spring Boot AI

A basic Spring Boot application with REST API for login/logout and item list/details, backed by PostgreSQL.

## Requirements

- Java 17+
- Maven 3.6+
- PostgreSQL 12+

## Database Setup

1. Create a database:

```sql
CREATE DATABASE spring_boot_ai;
```

2. Update `src/main/resources/application.yml` if needed:

- `spring.datasource.url`: `jdbc:postgresql://localhost:5432/spring_boot_ai`
- `spring.datasource.username` / `spring.datasource.password`: your PostgreSQL credentials

3. On first run, Flyway will create the schema and seed data:
   - **users**: `id`, `username`, `password_hash`, `created_at`
   - **items**: `id`, `name`, `description`, `created_at`
   - Demo user: `demo` / `password`
   - Three sample items

## Build and Run

```bash
mvn spring-boot:run
```

Server runs at `http://localhost:8080`.

## API Endpoints

### Login controller

| Method | Path       | Description                    | Auth   |
|--------|------------|--------------------------------|--------|
| POST   | /api/login | Login with JSON body           | No     |
| POST   | /api/logout| Logout (invalidates session)   | No     |

**Login request body:**
```json
{ "username": "demo", "password": "password" }
```

**Login response (200):**
```json
{ "success": true, "message": "Login successful", "username": "demo" }
```

### Item controller

| Method | Path          | Description        | Auth   |
|--------|---------------|--------------------|--------|
| GET    | /api/items    | List all items     | Yes    |
| GET    | /api/items/{id}| Item details by id | Yes    |

Item endpoints require an authenticated session (login first; the session cookie is then used for subsequent requests).

## Example (curl)

```bash
# Login (creates session)
curl -X POST http://localhost:8080/api/login \
  -H "Content-Type: application/json" \
  -d '{"username":"demo","password":"password"}' \
  -c cookies.txt -b cookies.txt

# List items (use same cookie jar)
curl -b cookies.txt http://localhost:8080/api/items

# Item details
curl -b cookies.txt http://localhost:8080/api/items/1

# Logout
curl -X POST http://localhost:8080/api/logout -b cookies.txt
```
