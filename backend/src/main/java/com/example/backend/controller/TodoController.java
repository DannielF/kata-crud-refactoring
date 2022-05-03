package com.example.backend.controller;

import com.example.backend.entity.Todo;
import com.example.backend.service.TodoService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping()
    public List<Todo> list() {
        return service.list();
    }

    @CrossOrigin
    @PostMapping()
    public Todo save(@RequestBody Todo todo) {
        return service.save(todo);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public Todo update(@PathVariable("id") Long id, @RequestBody Todo todo) {
        return service.update(id, todo);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Todo get(@PathVariable("id") Long id) {
        return service.get(id);
    }
}
