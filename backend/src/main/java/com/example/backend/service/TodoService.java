package com.example.backend.service;

import com.example.backend.dto.TodosDto;
import com.example.backend.entity.Todo;
import com.example.backend.repository.TodoRepository;
import com.example.backend.service.interfaces.ITodo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Todos service
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@Service
public class TodoService implements ITodo {

    private final TodoRepository repository;
    private final ModelMapper mapper;

    //field injection
    public TodoService(TodoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * List all todos
     * @return List<TodosDto>
     */
    @Transactional(readOnly = true)
    public List<TodosDto> list() {
        log.debug("Get all todos");
        return repository.findAll().stream()
                .map(this::mapToTodoDto)
                .toList();
    }

    /**
     * Map a todosDto to a todo then save it
     * @param todo Object
     * @return TodosDto
     */
    @Transactional
    public TodosDto save(TodosDto todo) {
        log.debug("Todo created: " + todo);
        Todo todoModel = mapToTodo(todo);
        if (todoModel.getTodoList() == null) {
            log.error("There's must be a list");
            throw new RuntimeException("There must be a list");
        }
        Todo saveTodo = repository.save(todoModel);
        return mapper.map(saveTodo, TodosDto.class);
    }

    /**
     * Map a todosDto to a todo then updated
     * @param id Long
     * @param todo Object
     * @return TodosDto
     */
    @Transactional
    public TodosDto update(Long id, TodosDto todo) {

        todo.setId(id);
        Todo todoModel = mapToTodo(todo);
        Todo saveTodo = repository.save(todoModel);
        return mapper.map(saveTodo, TodosDto.class);
    }

    /**
     * Delete a todo by id
     * @param id Long
     * @return Todo
     */
    @Transactional
    public Todo deleteById(Long id) {
        var todo = repository.findById(id);
        if (todo.isPresent()) {
            log.debug("Todo deleted with id: " + id);
            repository.delete(todo.get());
            return todo.get();
        }
        log.error("There's no such todo");
        return null;
    }

    /**
     * Get a todo by id
     * @param id Long
     * @return Todo
     */
    @Transactional(readOnly = true)
    public Todo getById(Long id) {
        Optional<Todo> todo = repository.findById(id);
        if (todo.isPresent()) {
            log.debug("Todo : " + todo.get());
            return todo.get();
        }
        log.error("There's no such todo");
        return null;
    }

    /**
     * Map a todo to a TodosDto
     * @param todo Object
     * @return TodosDto
     */
    private TodosDto mapToTodoDto(Todo todo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TodosDto todosDto;
        todosDto = mapper.map(todo, TodosDto.class);
        return todosDto;
    }

    /**
     * Map a todosDto to a todo
     * @param todo Object
     * @return Todo
     */
    private Todo mapToTodo(TodosDto todo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        Todo todoModel;
        todoModel = mapper.map(todo, Todo.class);
        return todoModel;
    }
}
