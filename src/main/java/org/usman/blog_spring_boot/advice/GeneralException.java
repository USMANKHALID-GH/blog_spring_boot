package org.usman.blog_spring_boot.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.usman.blog_spring_boot.error.IdNotFoundException;
import org.usman.blog_spring_boot.error.PhraseNotFoundEXception;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralException {



// entity validation error
    @ResponseStatus(value = HttpStatus.BAD_REQUEST,code = HttpStatus.INTERNAL_SERVER_ERROR)
   @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> GeneralExeption(MethodArgumentNotValidException ex){
        Map<String ,String> map= new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> map.put(fieldError.getField(),fieldError.getDefaultMessage()));

        return map;

   }

//   id not found
    @ResponseStatus(value = HttpStatus.NOT_FOUND,code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(IdNotFoundException.class)
    public Map<String, String> notFound(IdNotFoundException ex){
        Map<String ,String> map= new HashMap<>();
       map.put("IdError",ex.getMessage());

        return map;

    }

//    phrase returning empty
    @ResponseStatus(value = HttpStatus.NOT_FOUND ,code = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(PhraseNotFoundEXception.class)
    public Map<String, String> PhraseNotFound(PhraseNotFoundEXception ex){
        Map<String ,String> map= new HashMap<>();
        map.put("phraseError",ex.getMessage());

        return map;

    }

}
