# PatientCare API

A Spring Boot REST API that registers doctors and patients and **matches each patient to suitable doctors** by city and by the medical speciality that treats their symptom.

`Java 17` · `Spring Boot 2.7` · `Spring Data JPA` · `Hibernate Validator` · `MySQL` · `JUnit 5 / Mockito`

## Architecture

```
Controller  ──►  Service (interface + impl)  ──►  Repository (Spring Data JPA)  ──►  MySQL
   │
   └── @RestControllerAdvice → uniform 400 responses listing every validation error
```

- **Layered design.** Controllers only handle HTTP, the matching rules live in the service layer, and persistence goes through Spring Data repositories.
- **Validation at the edge.** Bean Validation constraints (name format, 10-digit phone, email, allowed cities, specialities and symptoms) are enforced on every write, and failures come back as a structured error body.
- **Symptom → speciality matching.** Symptoms map to specialities, for example *Arthritis / Backpain / Tissue injuries → Orthopedic*, *Skin infection / Skin burn → Dermatology*, *Ear pain → ENT* and *Dysmenorrhea → Gynecology*. The matcher returns the doctors in the patient's city with the right speciality.
- **Tests.** Service- and controller-layer unit tests are in `src/test`.

## API

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/doctor/create` | Register a doctor |
| `GET` | `/doctor/get-list` | List all doctors |
| `GET` | `/doctor/getById?id=` | Get a doctor by id |
| `GET` | `/doctor/listByCity?city=` | Doctors in a city |
| `GET` | `/doctor/listByCityAndSpeciality?city=&speciality=` | Doctors by city + speciality |
| `PATCH` | `/doctor/updateNumber?id=` | Update a doctor's phone number |
| `DELETE` | `/doctor/deleteById?id=` | Remove a doctor |
| `GET` | `/doctor/findByLocationAndSymptom?id=` | **Match** doctors for patient `id` |
| `POST` | `/patient/create` | Register a patient |
| `GET` | `/patient/list` | List all patients |

Example: register a doctor

```bash
curl -X POST localhost:8081/doctor/create -H 'Content-Type: application/json' -d '{
  "name": "Asha Verma", "city": "Delhi", "speciality": "Orthopedic",
  "email": "asha@example.com", "phoneNumber": "9876543210"
}'
```

## Run locally

Prerequisites: JDK 17, Maven, and a running MySQL instance with a database named `magic`.

```bash
export DB_PASSWORD=<your-mysql-password>   # optional: DB_URL, DB_USERNAME
mvn spring-boot:run                        # starts on :8081
mvn test                                   # run the test suite
```

## Roadmap

- [ ] Upgrade to Spring Boot 3 and switch to constructor injection
- [ ] Resource-oriented routes (`GET /doctors/{id}`, `GET /doctors?city=`) with correct status codes and pagination
- [ ] DTOs on every endpoint, so API contracts are separate from JPA entities
- [ ] Flyway migrations, integration tests with Testcontainers, and Docker Compose for one-command setup
- [ ] OpenAPI/Swagger docs and a GitHub Actions CI pipeline
