package org.lucidant.springboot.events;

import org.junit.jupiter.api.Test;
import org.lucidant.springboot.jpa.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_CLASS;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup({
    @Sql(value = "classpath:data/data-h2.sql", executionPhase = BEFORE_TEST_CLASS)
})
class EventControllerIT {

    @LocalServerPort
    private int port;

    @Autowired
    private WebTestClient webTestClient;

    @SuppressWarnings("DataFlowIssue")
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

        assertThat(event.getName()).isEqualTo("Globomantics Tech Conference");
        assertThat(event.getVenue().getName()).isEqualTo("Globomatics Main Office");
    }

}
