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

/**
 * TodoList Rest Controller
 *
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/todo-list")
public class TodoListController {

    private final TodoListService service;
    private final Response response = new Response();
    private final GetErrorMessages getErrorMessages;
    private HttpStatus httpStatus = HttpStatus.OK;

    public TodoListController(TodoListService service, GetErrorMessages getErrorMessages) {
        this.service = service;
        this.getErrorMessages = getErrorMessages;
    }

    /**
     * Get all TodoList
     * @return ResponseEntity<>
     */
    @GetMapping()
    public ResponseEntity<Response> list() {
        response.restart();
        try {
            response.data = service.list();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            getErrorMessages.getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Get a todoList by Id
     * @param id Long
     * @return ResponseEntity<>
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> get(@PathVariable(value = "id") Long id) {
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
        } catch (DataAccessException de) {
            getErrorMessages.getErrorMessageForResponse(de);
        } catch (Exception e) {
            getErrorMessages.getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Save a todoList
     * @param todoList Object
     * @return ResponseEntity<>
     */
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> save(@RequestBody TodoList todoList) {
        response.restart();
        try {
            log.info("TodoList Created : {}", todoList);
            response.data = service.save(todoList);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException de) {
            getErrorMessages.getErrorMessageForResponse(de);
        } catch (Exception e) {
            getErrorMessages.getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Update a todoList
     * @param id Long
     * @param todoList Object
     * @return ResponseEntity<>
     */
    @CrossOrigin
    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> update(@PathVariable(value = "id") Long id, @RequestBody TodoList todoList) {
        response.restart();
        try {
            log.info("TodoList updated : {}", todoList);
            response.data = service.update(id, todoList);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException de) {
            getErrorMessages.getErrorMessageForResponse(de);
        } catch (Exception e) {
            getErrorMessages.getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Delete a todoList by id
     * @param id Long
     * @return ResponseEntity<>
     */
    @CrossOrigin
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Response> delete(@PathVariable(value = "id") Long id) {
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
        } catch (DataAccessException de) {
            getErrorMessages.getErrorMessageForResponse(de);
        } catch (Exception e) {
            getErrorMessages.getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }
}
