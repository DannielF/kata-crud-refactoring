package com.example.backend.service.interfaces;

import com.example.backend.dto.TodosDto;
import com.example.backend.entity.Todo;

import java.util.List;

/**
 * Interface todos entity
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ITodo {

    public List<TodosDto> list();

    public TodosDto save(TodosDto todo);

    public TodosDto update(Long id, TodosDto todo);

    public Todo deleteById(Long id);

    public Todo getById(Long id);
}
