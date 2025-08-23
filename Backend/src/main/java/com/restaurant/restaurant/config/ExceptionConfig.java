package com.restaurant.restaurant.config;

import com.restaurant.restaurant.controller.vm.ExceptionResponse;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){

        return ResponseEntity.badRequest().body(new ExceptionResponse(exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ExceptionResponse>> handleException(MethodArgumentNotValidException exception){
        List<ExceptionResponse> exceptionResponses=new ArrayList<>();
       exception.getBindingResult().getFieldErrors().stream().forEach(fieldError -> {
           exceptionResponses.add(new ExceptionResponse(fieldError.getDefaultMessage()));
       });

        return ResponseEntity.badRequest().body(exceptionResponses);
    }
}
