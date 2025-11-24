# Appointment System Gateway
 - This is [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) which provide route to APIs and provide cross-cutting concerns to them such as security.
 - It relays token to microservices using ``TokenRelay`` feature
 - It build with Webflux because:
   - Reactive and Non-blocking: Leverages the reactive programming model with Project Reactor, enabling non-blocking I/O and efficient resource utilization. This is ideal for high-concurrency scenarios and real-time applications where low latency is critical.
```yaml
spring:
  application:
    name: appointment-gateway
    gateway:
      server:
        webflux:
          default-filters:
            - TokenRelay=
          routes:
            - id: branch-service
              uri: http://branch-service:branch-service-port
              predicates:
                - Path=/api/v1/branch/**,/api/v1/province/**,/api/v1/city/**
            - id: appointment-service
              uri: http://appointment-service:appointment-service-port
              predicates:
                - Path=/api/v1/appointment/**,/api/v1/slot/**
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://auth-server:9000/oauth2/jwks
          issuer-uri: http://auth-server:9000
app:
  open-api:
    info:
      title: Appointment API Gateway Service
      description: Appointment API Gateway Service
      version: 1.0.0
  cors:
    allowed-origins:
      - http://ui:4200 # UI
    allowed-methods:
      - "*"
    allowed-headers:
      - "*"
    max-age: 3600
  white-list:
    - "/v3/api-docs/**"
    - "/swagger-ui/**"
    - "/auth-service/swagger-ui/**"
    - "/auth-service/v3/api-docs/**"
    - "/api/v1/account/**"
    - "/branch-service/swagger-ui/**"
    - "/branch-service/v3/api-docs/**"
    - "/appointment-service/swagger-ui/**"
    - "/appointment-service/v3/api-docs/**"
  admin-routes:
    - "/api/v1/branch/admin/**"
springdoc:
  api-docs:
    path: /v3/api-docs
  swagger-ui:
    path: /swagger-ui.html
    operations-sorter: method
    doc-expansion: none
    urls:
      - name: gateway-service
        url: ${springdoc.api-docs.path}
      - name: branch-service
        url: http://branch-service:9002/branch-service/v3/api-docs
      - name: appointment-service
        url: http://appointment-service:9003/appointment-service/v3/api-docs
```