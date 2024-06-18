/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.advice;

import com.aptos.aptos.dto.ApiError;
import com.aptos.aptos.exception.CedulaExisteException;
import com.aptos.aptos.exception.MalformedProjectNameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Leonardo
 */
@RestControllerAdvice
public class ControllerAdvice {
  
    @ExceptionHandler(value = CedulaExisteException.class)
    public ResponseEntity<ApiError> CedulaExisteExceptionHandler(CedulaExisteException ex){
    ApiError error = ApiError.builder().code("a-100").message(ex.getMessage()).exception("CedulaExisteException").build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
        @ExceptionHandler(value = MalformedProjectNameException.class)
    public ResponseEntity<ApiError> MalformedProjectNameExceptionHandler(MalformedProjectNameException ex){
    ApiError error = ApiError.builder().code("a-101").message(ex.getMessage()).exception("MalformedProjectNameException").build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
