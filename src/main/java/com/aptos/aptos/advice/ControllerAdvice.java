/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aptos.aptos.advice;

import com.aptos.aptos.dto.ApiError;
import com.aptos.aptos.exception.CedulaExisteException;
import com.aptos.aptos.exception.MalformedProjectNameException;
import java.time.format.DateTimeParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author Leonardo
 */
@RestControllerAdvice
public class ControllerAdvice {
  
//    @ExceptionHandler(value = CedulaExisteException.class)
//    public ResponseEntity<ApiError> CedulaExisteExceptionHandler(CedulaExisteException ex){
//    ApiError error = ApiError.builder().code("a-100").message(ex.getMessage()).exception("CedulaExisteException").build();
//    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
//    }
    
        @ExceptionHandler(value = MalformedProjectNameException.class)
    public ResponseEntity<ApiError> MalformedProjectNameExceptionHandler(MalformedProjectNameException ex){
    ApiError error = ApiError.builder().code("a-101").message(ex.getMessage()).exception("MalformedProjectNameException").build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    
            @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException ex){
    ApiError error = ApiError.builder().code("a-102").message("Debes colocar numeros enteros no letras, no decimales").exception("HttpMessageNotReadableException").build();
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
      @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiError> HttpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException ex) {
        ApiError error = ApiError.builder().code("a-103").message("El metodo no es compatible con la ruta")
                .exception("NoResourceFoundException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
      
    }
    
        @ExceptionHandler(value = DateTimeParseException.class)
    public ResponseEntity<ApiError> MissingServletRequestParameterExceptionHandler(DateTimeParseException ex) {
        ApiError error = ApiError.builder().code("a-104").message("El formato de fecha no coincide con el esperado 'yyyy-MM-dd'")
                .exception("MissingServletRequestParameterException")
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); 
    }
}

