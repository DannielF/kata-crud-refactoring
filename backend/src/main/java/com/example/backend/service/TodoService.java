package com.example.backend.service;

import com.example.backend.dto.TodosDto;
import com.example.backend.repository.TodoRepository;
import com.example.backend.service.interfaces.ITodo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TodoService implements ITodo {

    private final TodoRepository repository;
    private final ModelMapper mapper;

    public TodoService(TodoRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<TodosDto> list() {
        log.debug("Get all todos");
        return repository.findAll().stream()
                .map(this::mapToTodoDto)
                .collect(Collectors.toList());
    }


    @Transactional
    public TodosDto save(TodosDto todo) {
        log.debug("Todo created: " + todo);
        com.example.backend.entity.Todo todoModel = mapToTodo(todo);
        if (todoModel.getTodoList() == null) {
            log.error("There must be a list");
            throw new RuntimeException("There must be a list");
        }
        com.example.backend.entity.Todo saveTodo = repository.save(todoModel);
        return mapper.map(saveTodo, TodosDto.class);
    }

    @Transactional
    public com.example.backend.entity.Todo update(Long id, com.example.backend.entity.Todo todo) {
        log.debug("Todo updated with id: " + id);
        todo.setId(id);
        return repository.save(todo);
    }

    @Transactional
    public com.example.backend.entity.Todo deleteById(Long id) {
        var todo = repository.findById(id);
        if (todo.isPresent()) {
            log.debug("Todo deleted with id: " + id);
            repository.delete(todo.get());
            return todo.get();
        }
        log.error("There's no such todo");
        return null;
    }

    @Transactional(readOnly = true)
    public com.example.backend.entity.Todo getById(Long id) {
        var todo = repository.findById(id);
        if (todo.isPresent()) {
            log.debug("Todo : " + todo.get());
            return todo.get();
        }
        log.error("There's no such todo");
        return null;
    }

    private TodosDto mapToTodoDto(com.example.backend.entity.Todo todo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        TodosDto todosDto;
        todosDto = mapper.map(todo, TodosDto.class);
        return todosDto;
    }

    private com.example.backend.entity.Todo mapToTodo(TodosDto todo) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        com.example.backend.entity.Todo todoModel;
        todoModel = mapper.map(todo, com.example.backend.entity.Todo.class);
        return todoModel;
    }
}
