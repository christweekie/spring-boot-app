package org.lucidant.springboot.events;

import lombok.extern.slf4j.Slf4j;
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
        final var todoSaved = repository.findById(1).get();

        assertThat(todoSaved.getId()).isEqualTo(1);
        assertThat(todoSaved.getTitle()).isEqualTo("Sent title");
        assertThat(todoSaved.isCompleted()).isTrue();
        assertThat(todoSaved.getUserId()).isEqualTo(100);
    }

}
