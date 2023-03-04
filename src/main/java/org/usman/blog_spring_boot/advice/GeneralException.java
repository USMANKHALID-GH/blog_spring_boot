package org.usman.blog_spring_boot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.usman.blog_spring_boot.Error.IdNotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralException {




    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> GeneralExeption(MethodArgumentNotValidException ex){
        Map<String ,String> map= new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> map.put(fieldError.getField(),fieldError.getDefaultMessage()));

        return map;

   }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(IdNotFoundException.class)
    public Map<String, String> notFound(IdNotFoundException ex){
        Map<String ,String> map= new HashMap<>();
       map.put("ErrorMesage",ex.getMessage());

        return map;

    }

}
