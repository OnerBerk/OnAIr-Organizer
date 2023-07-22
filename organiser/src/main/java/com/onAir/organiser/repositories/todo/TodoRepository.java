package com.onAir.organiser.repositories.todo;

import com.onAir.organiser.domain.todo.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
