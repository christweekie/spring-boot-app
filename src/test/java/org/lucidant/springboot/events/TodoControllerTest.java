package org.lucidant.springboot.events;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lucidant.springboot.jpa.entity.Todo;
import org.lucidant.springboot.jpa.repo.ToDoRepository;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class TodoControllerTest {

	private static final ObjectMapper objectMapper = new ObjectMapper();
	private static MockWebServer mockBackEnd;

	@Mock
	private ToDoRepository toDoRepository;

	private TodoController controller;

	private static int port;

	@BeforeAll
	static void beforeAll() throws IOException {
		mockBackEnd = new MockWebServer();
		mockBackEnd.start();

		port = mockBackEnd.getPort();
	}

	@BeforeEach
	void beforeEach() {
		final var baseUrl = String.format("http://localhost:%s", port);

		final var webClient = WebClient.builder()
			.baseUrl(baseUrl)
//			.clientConnector(new ReactorClientHttpConnector(httpClient))
			.build();
		controller = new TodoController(webClient, toDoRepository);
	}

	@AfterAll
	static void afterAll() throws IOException {
		mockBackEnd.shutdown();
	}

	@Test
	void testGet() throws JsonProcessingException, InterruptedException {
		Todo mockTodo = Todo.builder()
			.id(1)
			.title("Initial Title")
			.completed(false)
			.userId(100)
			.build();

		mockBackEnd.enqueue(new MockResponse()
			.setBody(objectMapper.writeValueAsString(mockTodo))
			.addHeader("Content-Type", "application/json"));

		Mono<Todo> todoMono = controller.getTodo(1);

		StepVerifier.create(todoMono)
			.expectNextMatches(todo -> todo.getTitle().equals("Initial Title"))
			.verifyComplete();

		RecordedRequest recordedRequest = mockBackEnd.takeRequest();
		assertEquals("GET", recordedRequest.getMethod());
		assertEquals("/todo/1", recordedRequest.getPath());

	}
}
