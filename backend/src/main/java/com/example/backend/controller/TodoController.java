package com.example.backend.controller;

import com.example.backend.dto.TodosDto;
import com.example.backend.service.TodoService;
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
 * Todos Rest Controller
 *
 * @author Daniel Granados
 * @version 1.0.0
 * @since 1.0.0
 */
@Slf4j
@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("/todo")
public class TodoController {

    private final TodoService service;
    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;

    public TodoController(TodoService service) {
        this.service = service;
    }

    /**
     * Get all Todos
     * @return ResponseEntity<>
     */
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

    /**
     * Get a todo by Id
     * @param id Long
     * @return ResponseEntity<>
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<Response> get(@PathVariable(value = "id") Long id) {
        response.restart();
        try {
            response.data = service.getById(id);
            if (response.data == null) {
                response.message = "There's no such Todo";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Todo found";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            GetErrorMessages.getErrorMessageForResponse(exception, response, httpStatus);
        } catch (Exception exception) {
            GetErrorMessages.getErrorMessageInternal(exception, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Save a todo
     * @param todo Object
     * @return ResponseEntity<>
     */
    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> save(@RequestBody TodosDto todo) {
        response.restart();
        try {
            log.info("Todo Created : {}", todo);
            response.data = service.save(todo);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException dae) {
            GetErrorMessages.getErrorMessageForResponse(dae, response, httpStatus);
        } catch (Exception e) {
            GetErrorMessages.getErrorMessageInternal(e, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Update a todo
     * @param id Long
     * @param todo Object
     * @return ResponseEntity<>
     */
    @CrossOrigin
    @PutMapping(path = "/{id}")
    public ResponseEntity<Response> update(@PathVariable(value = "id") Long id, @RequestBody TodosDto todo) {
        response.restart();
        try {
            log.info("Todo updated : {}", todo);
            response.data = service.update(id, todo);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException dae) {
            GetErrorMessages.getErrorMessageForResponse(dae, response, httpStatus);
        } catch (Exception e) {
            GetErrorMessages.getErrorMessageInternal(e, response, httpStatus);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Delete a todo by id
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
                response.message = "There's no such Todo";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Todo deleted";
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
