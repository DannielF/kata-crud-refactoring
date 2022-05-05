package com.example.backend.controller;

import com.example.backend.dto.TodosDto;
import com.example.backend.entity.Todo;
import com.example.backend.service.TodoService;
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

import java.sql.SQLException;

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

    @CrossOrigin
    @GetMapping()
    public ResponseEntity<Response> list() {
        response.restart();
        try {
            response.data = service.list();
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @PostMapping()
    public ResponseEntity<Response> save(@RequestBody TodosDto todo) {
        response.restart();
        try {
            log.info("Todo Created : {}", todo);
            response.data = service.save(todo);
            httpStatus = HttpStatus.CREATED;
        } catch (DataAccessException dae) {
            getErrorMessageForResponse(dae);
        } catch (Exception e) {
            getErrorMessageInternal(e);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable("id") Long id, @RequestBody Todo todo) {
        response.restart();
        try {
            log.info("Todo updated : {}", todo);
            response.data = service.update(id, todo);
            httpStatus = HttpStatus.OK;
        } catch (DataAccessException dae) {
            getErrorMessageForResponse(dae);
        } catch (Exception e) {
            getErrorMessageInternal(e);
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
                response.message = "There's no such Todo";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Todo deleted";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
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
                response.message = "There's no such Todo";
                httpStatus = HttpStatus.NOT_FOUND;
            } else {
                response.message = "Todo found";
                httpStatus = HttpStatus.OK;
            }
        } catch (DataAccessException exception) {
            getErrorMessageForResponse(exception);
        } catch (Exception exception) {
            getErrorMessageInternal(exception);
        }
        return new ResponseEntity<>(response, httpStatus);
    }

    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageInternal(Exception exception) {
        response.error = true;
        response.message = exception.getMessage();
        response.data = exception.getCause();
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    /**
     * Administrador para las excepciones a nivel de SQL con respecto al manejo del acceso a los datos
     *
     * @param exception Objeto DataAccessException
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    private void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException) {
            SQLException sqlEx = (SQLException) exception.getRootCause();
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062:
                    response.message = "El dato ya está registrado";
                    break;
                case 1452:
                    response.message = "El usuario indicado no existe";
                    break;
                default:
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
