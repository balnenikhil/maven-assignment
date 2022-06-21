package com.spring.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandler {

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = NotFoundException.class)
    public String notFoundException(Model model){

        model.addAttribute("message", "requested resource not found" );
        return "Exception";
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    public String exception(Model model){

        model.addAttribute("message", "Exception has Occurred" );

        return "Exception";
    }
}
