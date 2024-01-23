package org.lucidant.springboot.advice;

import java.util.NoSuchElementException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ControllerExceptionHandlerTest {

    @Test
    void givenError_whenException_then404() {
        final var response = new ControllerExceptionHandler().notFound(new NoSuchElementException("Message"));

        assertThat(response.getStatusCode().is4xxClientError()).isTrue();
        assertThat(response.getTitleMessageCode()).contains("NoSuchElementException");
    }
}
