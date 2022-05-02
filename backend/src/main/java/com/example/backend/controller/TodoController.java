package com.example.backend.controller;

import com.example.backend.entity.Todo;
import com.example.backend.service.TodoService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Controller
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @GetMapping(path = "/todos")
    public List<Todo> list() {
        return service.list();
    }

    @PostMapping(path = "/todo")
    public Todo save(@RequestBody Todo todo) {
        return service.save(todo);
    }

    @PutMapping(path = "/todo/{id}")
    public Todo update(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return service.update(id, todo);
    }

    @DeleteMapping(path = "/todo/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping(path = "/todo/{id}")
    public Todo get(@PathVariable("id") Long id) {
        return service.get(id);
    }
}
