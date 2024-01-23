# Spring Boot 3 Fundamentals

## Purpose

The purpose of this project is a place to consolidate config, examples, test case samples, etc for Spring and in particular Spring Boot.

### Running it

There are MongoDB and postgres services in docker-compose. 

    docker compose up -d

To start the spring boot application 

    mvn spring-boot:run

It's a maven project obviously so the rest of it should just follow that standard.

### Exercise the endpoints

There are some HTTP request files in the `http-request` folder to exercise the endpoints. These work in IntelliJ.  

### Test Features

https://rieckpil.de/spring-boot-testing-mockmvc-vs-webtestclient-vs-testresttemplate/

MockMvc - fast, doesn't start teh application server
WebTestClient - this is good for testing WebFlux endpoints
TestRestTemplate

|                  | Web MVC test ? | Test WebFlux | Invoke Mock Servlet | Over HTTP | Server side views |
|------------------|----------------|--------------|---------------------|-----------|-------------------|
| MockMvc          | ✅             | ❌           | ✅                  | ❌         | ✅                 |
| WebTestClient    | ✅             | ✅           | ✅                  | ✅         | ❌                 |
| TestRestTemplate | ✅             | ❌           | ❌                  | ✅         | ❌                 |

`WebTestClient` is the way to go because it does everything that `TestRestTemplate` does.
`MockMvc` is good because it doesn't have to start the whole server.

#### Spring Boot Actuator
There are the built-in health and info endpoints which are enabled with the test profile.

### Other Project Features 

CircleCI integration for pipelines 

Dependabot (via GitHub) for automated dependency management

