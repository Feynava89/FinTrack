
package com.fintrack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,String>>manejoError(IllegalArgumentException exception){
        Map<String,String> respuesta=new HashMap<>();

        respuesta.put("error",exception.getMessage());

        HttpStatus status=exception.getMessage()
            .startsWith("No se encontro")
            ? HttpStatus.NOT_FOUND
            : HttpStatus.BAD_REQUEST;

        return ResponseEntity.status(status).body(respuesta); 
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarValidaciones(MethodArgumentNotValidException exception){
        Map<String, String> errores = new HashMap<>();

        exception.getBindingResult()
            .getFieldErrors()
            .forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
            );

        return ResponseEntity
            .badRequest()
            .body(errores);
    }
}