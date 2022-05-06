package com.example.backend.repository;

import com.example.backend.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * TodoList Repository
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
}