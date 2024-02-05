package org.lucidant.springboot.events;

import lombok.extern.slf4j.Slf4j;
import org.lucidant.springboot.jpa.entity.Todo;
import org.lucidant.springboot.jpa.repo.ToDoRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
public class TodoController {

    private static final String PATH_PARAM_BY_ID = "/todo/{id}";
    private final WebClient webClient;
    private final ToDoRepository repository;

    public TodoController( WebClient webClient,
                            ToDoRepository repository) {
        this.webClient = webClient;
        this.repository = repository;
    }

    @GetMapping(path = "/todos/{id}")
    public Mono<Todo> getTodo(@PathVariable("id") final int todoId) {
        return webClient.get()
            .uri(PATH_PARAM_BY_ID, todoId)
            .header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
            .retrieve()
            .bodyToMono(Todo.class);
    }



    @SuppressWarnings("BlockingMethodInNonBlockingContext")
    @PutMapping(path="/todo/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Todo> upsert(@PathVariable("id") String id, @RequestBody Todo todo) {

        final var savedToDO =  this.repository.findById(Integer.parseInt(id)).map(value -> {
                value.setTitle(todo.getTitle());
                value.setCompleted(todo.isCompleted());
                return value; // Can this be done cleaner?
            })
            .orElse(todo);

        return Mono.just(repository.save(savedToDO));
    }

}
