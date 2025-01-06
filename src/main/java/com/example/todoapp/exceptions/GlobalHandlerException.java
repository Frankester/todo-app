package com.example.todoapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(CategoriaNoExisteException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String categoriaNoExiste(CategoriaNoExisteException ex){
        return ex.getLocalizedMessage();
    }

}
