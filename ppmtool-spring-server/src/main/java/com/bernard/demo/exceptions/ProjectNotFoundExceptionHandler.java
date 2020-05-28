package com.bernard.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProjectNotFoundExceptionHandler extends RuntimeException{

    public ProjectNotFoundExceptionHandler(String message) {
        super(message);
    }
}
