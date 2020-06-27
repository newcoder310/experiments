# Eureka Server

This is part of the sample architecture that acts as the service discovery for the microservices `stock-service` and `db-service`.
It uses:
- `Eureka` wrapped by `spring-cloud` for service discovery.
- `zuul` wrapped by `spring-cloud` that acts as api-gateway
