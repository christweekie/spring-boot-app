package org.lucidant.springboot.todo;

import org.lucidant.springboot.jpa.entity.Todo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface TodoClient {

	 String PATH_PARAM_BY_ID = "/todos/{id}";

	@GetExchange(PATH_PARAM_BY_ID)
	Todo getTodoById(@PathVariable("id") int todoId);

	@GetExchange("/todos/{id}")
	Mono<Todo> getTodoByIdMono(@PathVariable("id") int todoId);
}
