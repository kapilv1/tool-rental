
# Tool Rental – Spring Boot REST API (Full v2)

This project implements the Tool Rental system per the provided spec, exposing REST endpoints and
storing generated rental agreements in-memory for retrieval.

## Run
mvn spring-boot:run

## Test
mvn clean test

## Notes
- No external DB required. In-memory repos used for tools & agreements.
- Date format: MM/dd/yy. Currency: US locale. Rounding: HALF_UP.
