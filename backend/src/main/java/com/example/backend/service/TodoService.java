package com.example.backend.service;

import com.example.backend.entity.Todo;
import com.example.backend.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TodoService {

    private final TodoRepository repository;

    public TodoService(TodoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<Todo> list() {
        log.debug("Get all todos");
        return repository.findAll();
    }

    @Transactional
    public Todo save(Todo todo) {
        log.debug("Todo created: " + todo);
        return repository.save(todo);
    }

    @Transactional
    public Todo update(Long id, Todo todo) {
        log.debug("Todo updated with id: "+ id);
        todo.setId(id);
        return repository.save(todo);
    }

    @Transactional
    public void delete(Long id) {
        log.debug("Todo deleted with id: " + id);
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Todo get(Long id) {
        log.debug("Todo got with id: " + id);
        return repository.findById(id).orElseThrow();
    }

}
