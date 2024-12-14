package com.example.SpringBootProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDeatils>handleResourceNotFoundException(ResourceNotFoundException resourceNotFoundException,
                                                                       WebRequest webRequest){
        ErrorDeatils errorDeatils = new ErrorDeatils(
                LocalDateTime.now(),
                resourceNotFoundException.getMessage(),
                webRequest.getDescription(false),
                "400.0.1_USER_NOT_FOUND"
        );

        return new ResponseEntity<>(errorDeatils, HttpStatus.NOT_FOUND);


    }
}
