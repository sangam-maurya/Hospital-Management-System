package com.practo.excepction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobleHandelException {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handelException(Exception e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }
}
