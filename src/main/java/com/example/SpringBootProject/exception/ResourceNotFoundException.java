package com.example.SpringBootProject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{


    private String resourceId;

    public ResourceNotFoundException(String resourceId) {
        super(String.format("User is not found with id  : '%s'",  resourceId));
        this.resourceId = resourceId;
    }





}
