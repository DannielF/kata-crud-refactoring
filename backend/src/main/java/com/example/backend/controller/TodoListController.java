package com.example.backend.controller;

import com.example.backend.entity.TodoList;
import com.example.backend.service.TodoListService;
import com.example.backend.utils.GetErrorMessages;
import com.example.backend.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/todo-list")
public class TodoListController {

    private final TodoListService service;
    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    public TodoListController(TodoListService service) {
        this.service = service;
    }

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Response> list() {
        response.restart();
        try {
            response.data = service.list();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            GetErrorMessages.getErrorMessageInternal(e, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = service.getById(id);
            if (response.data == null) {
                response.message = "There's no such TodoList";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "TodoList found";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            GetErrorMessages.getErrorMessageForResponse(exception, response, httpStatus);
        } catch (Exception exception) {
            GetErrorMessages.getErrorMessageInternal(exception, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> save(@RequestBody TodoList todoList) {
        response.restart();
        try {
            log.info("TodoList Created : {}", todoList);
            response.data = service.save(todoList);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException dae) {
            GetErrorMessages.getErrorMessageForResponse(dae, response, httpStatus);
        } catch (Exception e) {
            GetErrorMessages.getErrorMessageInternal(e, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") Long id, @RequestBody TodoList todoList) {
        response.restart();
        try {
            log.info("TodoList updated : {}", todoList);
            response.data = service.update(id, todoList);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException dae) {
            GetErrorMessages.getErrorMessageForResponse(dae, response, httpStatus);
        } catch (Exception e) {
            GetErrorMessages.getErrorMessageInternal(e, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable("id") Long id) {
        response.restart();
        try {
            response.data = service.deleteById(id);
            if (response.data == null) {
                response.message = "There's no such TodoList";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "TodoList deleted";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            GetErrorMessages.getErrorMessageForResponse(exception, response, httpStatus);
        } catch (Exception exception) {
            GetErrorMessages.getErrorMessageInternal(exception, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
