package com.example.users.controllers.handler;

import com.example.users.core.dtos.ErrorsDTO;
import com.example.users.core.exceptions.InvalidUserDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(InvalidUserDataException.class)
    protected ResponseEntity<ErrorsDTO> handleNotFound(RuntimeException ex) {

        var errorData = ErrorsDTO
                .builder()
                .message(ex.getMessage())
                .code(HttpStatus.UNPROCESSABLE_ENTITY.name())
                .build();

        return new ResponseEntity<>(errorData, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
