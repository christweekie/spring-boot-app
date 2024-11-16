package org.lucidant.springboot.todo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.lucidant.springboot.jpa.entity.Todo;
import org.lucidant.springboot.jpa.repo.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Here we start a complete SpringBoot Application and hit it with the `WebTestClient`
 */
@Slf4j
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private ToDoRepository repository;

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private TodoClient todoClient;

    @BeforeEach
    void beforeEach() {
        log.info("Port is {}", port);
    }

    @Test
    void givenToDoExists_whenPut_thenInsert() {

        final var todoIn = Todo.builder()
                    .id(1)
                    .title("Sent title")
                    .completed(true)
                    .userId(100)
                    .build();

        this.webTestClient
            .put()
            .uri("/todo/1")
            .bodyValue(todoIn)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus()
            .isCreated()
            ;

        // Assert
        repository.findById(1).ifPresentOrElse((s) -> {
            assertThat(s.getId()).isEqualTo(1);
            assertThat(s.getTitle()).isEqualTo("Sent title");
            assertThat(s.isCompleted()).isTrue();
            assertThat(s.getUserId()).isEqualTo(100);
        }, Assertions::fail);
    }

}
