# Connector Platform

Spring Boot 3.2 multi-connector integration platform.

## Run
- `mvn spring-boot:run`

## Features
- Strategy-based connector framework
- Razorpay + HubSpot connectors
- Connector config/log persistence
- Rate limiting, retry helper, global exception handling
- Swagger at `/swagger-ui/index.html`
- testing at postman testing

## Database
- Local dev: H2
- Prod: MySQL profile
- Schema script: `scripts/schema.sql`
