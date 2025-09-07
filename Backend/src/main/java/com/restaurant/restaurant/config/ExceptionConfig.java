package com.restaurant.restaurant.config;

import com.restaurant.restaurant.Service.messageHandler.BundleMessageService;
import com.restaurant.restaurant.controller.vm.ExceptionResponse;
import com.restaurant.restaurant.setting.BundleMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionConfig {

    private BundleMessageService bundleMessageService;

    @Autowired
    public ExceptionConfig(BundleMessageService bundleMessageService) {
        this.bundleMessageService = bundleMessageService;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exception){

        return ResponseEntity.badRequest().body(
                new ExceptionResponse(
                        bundleMessageService.getMessage(exception.getMessage())));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleException(MethodArgumentNotValidException exception){
        FieldError fieldError = exception.getBindingResult().getFieldErrors().get(0);
        String error = fieldError.getDefaultMessage();
        BundleMessage bundleMessage =bundleMessageService.getMessage(error);

        return ResponseEntity.badRequest().body(new ExceptionResponse(bundleMessage));
    }
}
