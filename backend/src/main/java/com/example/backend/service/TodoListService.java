package com.example.backend.service;

import com.example.backend.entity.TodoList;
import com.example.backend.repository.TodoListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class TodoListService {

    private final TodoListRepository repository;

    public TodoListService(TodoListRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<TodoList> list() {
        log.debug("Get all todos");
        return repository.findAll();
    }

    @Transactional
    public TodoList save(TodoList todoList) {
        log.debug("TodoList created: " + todoList);
        return repository.save(todoList);
    }

    @Transactional
    public TodoList update(Long id, TodoList todoList) {
        log.debug("TodoList updated with id: "+ id);
        todoList.setId(id);
        return repository.save(todoList);
    }

    @Transactional
    public TodoList delete(Long id) {
        var todoList = repository.findById(id);
        if (todoList.isPresent()) {
            log.debug("TodoList deleted with id: " + id);
            repository.delete(todoList.get());
            return todoList.get();
        }
        return null;
    }

    @Transactional(readOnly = true)
    public TodoList get(Long id) {
        var todoList = repository.findById(id);
        if(todoList.isPresent()){
            log.debug("TodoList got with id: " + id);
            return todoList.get();
        } else {
            throw new RuntimeException("No existe todoList");
        }
    }


}
