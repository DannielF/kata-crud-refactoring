package com.example.backend.utils;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;

import java.sql.SQLException;

public class GetErrorMessages {

    private final Response response = new Response();
    private HttpStatus httpStatus = HttpStatus.OK;



    /**
     * Administrador para las excepciones del sistema
     *
     * @param exception Objeto Exception
     * @author Julian Lasso <julian.lasso@sofka.com.co>
     * @since 1.0.0
     */
    public void getErrorMessageInternal(Exception exception) {
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
    public void getErrorMessageForResponse(DataAccessException exception) {
        response.error = true;
        if (exception.getRootCause() instanceof SQLException sqlEx) {
            var sqlErrorCode = sqlEx.getErrorCode();
            switch (sqlErrorCode) {
                case 1062 -> response.message = "El dato ya está registrado";
                case 1452 -> response.message = "El usuario indicado no existe";
                default -> {
                    response.message = exception.getMessage();
                    response.data = exception.getCause();
                }
            }
            httpStatus = HttpStatus.BAD_REQUEST;
        } else {
            response.message = exception.getMessage();
            response.data = exception.getCause();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
