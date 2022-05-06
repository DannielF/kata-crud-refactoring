package com.example.backend.repository;

import com.example.backend.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Todo Repository
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

}
