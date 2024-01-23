package org.lucidant.springboot.controller;

import org.junit.jupiter.api.Test;
import org.lucidant.springboot.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

//@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EventControllerIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void givenExistingEvent_whenGet_thenReturn() {
        final Event event = this.webTestClient
            .get()
            .uri("/events/501")
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .exchange()
            .expectStatus()
            .isOk()
            .expectBody(Event.class)
            .returnResult()
            .getResponseBody();
        System.out.println(event);
    }
}
