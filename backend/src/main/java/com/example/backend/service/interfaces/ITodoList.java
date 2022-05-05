package com.example.backend.service.interfaces;

import com.example.backend.entity.TodoList;

import java.util.List;

public interface ITodoList {

    public List<TodoList> list();

    public TodoList save(TodoList todoList);

    public TodoList update(Long id, TodoList todoList);

    public TodoList deleteById(Long id);

    public TodoList getById(Long id);

}
