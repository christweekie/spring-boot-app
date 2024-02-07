package org.lucidant.springboot.todo;

import lombok.extern.slf4j.Slf4j;
import org.lucidant.springboot.jpa.entity.Todo;
import org.lucidant.springboot.jpa.repo.ToDoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Shows the use of the HttpClientInterface (@GetExchange, etc) annotations in Spring in reducing
 * WebClient (Reactive) boiler-plate code. Instead of 5 lines of repetitive code constructing a request,
 * there's one line in an interface. It's like Spring Data.
 */
@Slf4j
@RestController
public class TodoController {

    private final TodoClient todoWebClient;
    private final ToDoRepository repository;

    public TodoController(final TodoClient client,
                          final ToDoRepository repository) {
        this.todoWebClient = client;
        this.repository = repository;
    }

    @GetMapping(path = "/todos/http/{id}")
    public Todo getTodoById(@PathVariable("id") int todoId) {
        return todoWebClient.getTodoById(todoId);
    }

    @SuppressWarnings("java:S1135") // It's not a TO-DO, it's the name of a class :-)
    @GetMapping(path = "/todos/{id}")
    public Mono<Todo> getTodoByIdMono(@PathVariable("id") int todoId) {

//        return webClient_get()
//            _uri(PATH_PARAM_BY_ID, todoId)
//            _header(HttpHeaders.CONTENT_TYPE, APPLICATION_JSON_VALUE)
//            _retrieve()
//            _bodyToMono(Todo.class)_
        return todoWebClient.getTodoByIdMono(todoId);
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
