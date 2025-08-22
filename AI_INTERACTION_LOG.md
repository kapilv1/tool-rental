# AI Interaction Log

This log documents selective AI interactions that were used during the development of the **Tool Rental Spring Boot Application**.  
The goal was to leverage AI assistance only for **time-saving boilerplate generation and quick clarifications**, while the main architecture, design, and implementation were developed by the author.

---

### Prompt 1:
*"Can you give me a concise Spring Boot REST controller template with CRUD methods for an entity like `Tool`, including basic validation annotations?"*

Remarks: Used to quickly scaffold controller methods instead of writing boilerplate from scratch.

---

### Prompt 2:
*"Please generate a clean JPA entity mapping example for a `Tool` object with fields (code, type, brand, dailyCharge, weekendCharge, holidayCharge). I just need the annotations right."*

Remarks: Saved time setting up entity annotations and column mapping.

---

### Prompt 3:
*"What’s the simplest way to configure an in-memory repository with `ConcurrentHashMap` in Spring Boot for prototyping before I switch to JPA?"*

Remarks: Helped speed up prototyping without spending time on manual repository setup.

---

### Prompt 4:
*"Generate a JUnit 5 test class example that tests a Spring Boot REST controller with `@WebMvcTest` and `MockMvc`."*

Remarks: Used as a time-saver for test scaffolding to validate REST endpoints.

---

### Prompt 5:
*"Can you provide a small reusable utility in Java that checks if a given `LocalDate` is a weekend or matches a predefined list of holidays?"*

Remarks: Reduced time writing date utility logic for checkout calculations.

---

### Prompt 6:
*"Show me how to define a `GET /health` endpoint in Spring Boot that simply returns `{status: "UP"}` as JSON."*

Remarks: Quickly added a health check endpoint without overthinking.

---

## Notes
- AI was leveraged selectively to **avoid repetitive boilerplate coding**.
