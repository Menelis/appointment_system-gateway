# Appointment System Gateway
 - This is [Spring Cloud Gateway](https://spring.io/projects/spring-cloud-gateway) which provide route to APIs and provide cross-cutting concerns to them such as security.
 - It relays token to microservices using ``TokenRelay`` feature
 - It build with Webflux because:
   - Reactive and Non-blocking: Leverages the reactive programming model with Project Reactor, enabling non-blocking I/O and efficient resource utilization. This is ideal for high-concurrency scenarios and real-time applications where low latency is critical.
### Existing public Docker Image
 - There is an already existing public image you can use without building the new one if you not making code changes:
   - Image - ```docker.io/menelismthembu12/appointment-api-gateway```
   - Tag - ```1.0.2```
 - The service allows config to be externalized using config-server.
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
              uri: http://{service_name}:{namespace}:8080
              predicates:
                - Path=/api/v1/branch/**,/api/v1/province/**,/api/v1/city/**
            - id: appointment-service
              uri: http://{service_name}:{namespace}:8080
              predicates:
                - Path=/api/v1/appointment/**,/api/v1/slot/**
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://auth-server/oauth2/jwks
          issuer-uri: http://auth-server
app:
  open-api:
    info:
      title: Appointment API Gateway Service
      description: Appointment API Gateway Service
      version: 1.0.0
  cors:
    allowed-origins:
      - https://appointment-ui.local/ # UI
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
        url: http://{service_name}.{namespace}:8080/branch-service/v3/api-docs
      - name: appointment-service
        url: http://{service_name}.{namespace}:8080/appointment-service/v3/api-docs
```