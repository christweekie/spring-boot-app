# Spring Boot 3 Fundamentals

## Purpose

The purpose of this project is a place to consolidate config, examples, test case samples, etc for Spring and in particular Spring Boot.

### Running the Application

There is a dependency on a postgres services, expressed and runnable from the docker-compose. It can be done manually or in IntelliJ, there's a run config which should be automatically set up with the file `docker-local.run.xml` in the .run folder. 

    docker compose up -d

There's a SQL script that you can run `data-postgres.sql` to populate some tables. Tried getting this automatic wih sql.init.mode but it runs before the tables get created. 

To start the spring boot application 

    mvn spring-boot:run

It's a maven project obviously so the rest of it should just follow that standard.

### Exercise the endpoints

There are some HTTP request files in the `http-request` folder to exercise the endpoints. These work in IntelliJ.  

### Test Features

https://rieckpil.de/spring-boot-testing-mockmvc-vs-webtestclient-vs-testresttemplate/

MockMvc - fast, doesn't start the application server
WebTestClient - this is good for testing WebFlux endpoints
TestRestTemplate

|                  | Web MVC test ? | Test WebFlux | Invoke Mock Servlet | Over HTTP | Server side views |
|------------------|----------------|--------------|---------------------|-----------|-------------------|
| MockMvc          | ✅             | ❌           | ✅                  | ❌         | ✅                 |
| WebTestClient    | ✅             | ✅           | ✅                  | ✅         | ❌                 |
| TestRestTemplate | ✅             | ❌           | ❌                  | ✅         | ❌                 |

`WebTestClient` is the way to go because it does everything that `TestRestTemplate` does.
`MockMvc` is good because it doesn't have to start the whole server.


## Integration Tests

Use a mixture of each of the above named clients. 
The Spring "test" profile starts an in-memory H2 database and tests such as `EventControllerIT` populate it with some SqlGroup annotations as provided by Spring.

## Unit Tests

Yes, we have them. 


#### Spring Boot Actuator
There are the built-in health and info endpoints which are enabled with the test profile.

### Other Project Features 

CircleCI integration for pipelines 

Dependabot (via GitHub) for automated dependency management

