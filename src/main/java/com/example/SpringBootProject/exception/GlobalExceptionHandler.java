package com.example.SpringBootProject.exception;

import org.springframework.data.mongodb.core.mapping.FieldName;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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
    @ExceptionHandler(EmailAlreadyExists.class)

    public ResponseEntity<ErrorDeatils>handleEmailAlreadyExists(EmailAlreadyExists emailAlreadyExists,
                                                                       WebRequest webRequest) {
        ErrorDeatils errorDeatils = new ErrorDeatils(
                LocalDateTime.now(),
                emailAlreadyExists.getMessage(),
                webRequest.getDescription(false),
                "400.0.2_USER_ALREADY_EXISTS"
        );

        return new ResponseEntity<>(errorDeatils, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)

    public ResponseEntity<ErrorDeatils>handleGlobalException(Exception exception,
                                                                WebRequest webRequest) {
        ErrorDeatils errorDeatils = new ErrorDeatils(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "400.0.2_INTERNAL_SERVER_ERROR"
        );

        return new ResponseEntity<>(errorDeatils, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String,String> errors= new HashMap<>();
        List<ObjectError> objectErrorList=ex.getBindingResult().getAllErrors();
        objectErrorList.forEach((error ->{
            String fieldname = ((FieldError)error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldname,message);

        }));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
