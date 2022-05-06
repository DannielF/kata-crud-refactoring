package com.example.backend.service;

import com.example.backend.entity.TodoList;
import com.example.backend.repository.TodoListRepository;
import com.example.backend.service.interfaces.ITodoList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * TodoList service
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class TodoListService implements ITodoList {

    private final TodoListRepository repository;

    //field injection
    public TodoListService(TodoListRepository repository) {
        this.repository = repository;
    }

    /**
     * List all todoList
     * @return List<TodoList>
     */
    @Transactional(readOnly = true)
    public List<TodoList> list() {
        log.debug("Get all todos");
        return repository.findAll();
    }

    /**
     * Save a todoList
     * @param todoList Object
     * @return TodoList
     */
    @Transactional
    public TodoList save(TodoList todoList) {
        log.debug("TodoList created: " + todoList);
        return repository.save(todoList);
    }

    /**
     * Update a todoList
     * @param id Long
     * @param todoList Object
     * @return TodoList
     */
    @Transactional
    public TodoList update(Long id, TodoList todoList) {
        log.debug("TodoList updated with id: "+ id);
        todoList.setId(id);
        return repository.save(todoList);
    }

    /**
     * Delete a todoList by id
     * @param id Long
     * @return TodoList
     */
    @Transactional
    public TodoList deleteById(Long id) {
        var todoList = repository.findById(id);
        if (todoList.isPresent()) {
            log.debug("TodoList deleted with id: " + id);
            repository.delete(todoList.get());
            return todoList.get();
        }
        return null;
    }

    /**
     * Get a todoList by id
     * @param id Long
     * @return TodoList
     */
    @Transactional(readOnly = true)
    public TodoList getById(Long id) {
        var todoList = repository.findById(id);
        if(todoList.isPresent()){
            log.debug("TodoList got with id: " + id);
            return todoList.get();
        } else {
            throw new RuntimeException("There's no such TodoList");
        }
    }


}
