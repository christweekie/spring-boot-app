package org.lucidant.springboot.jpa.repo;

import org.lucidant.springboot.jpa.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Integer> {
}
