package com.example.backend.service.interfaces;

import com.example.backend.dto.TodosDto;
import com.example.backend.entity.Todo;

import java.util.List;

public interface ITodo {

    public List<TodosDto> list();

    public TodosDto save(TodosDto todo);

    public Todo update(Long id, Todo todo);

    public Todo deleteById(Long id);

    public Todo getById(Long id);
}
