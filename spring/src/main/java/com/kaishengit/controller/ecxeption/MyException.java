package com.kaishengit.controller.ecxeption;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class MyException {

    @ExceptionHandler(IOException.class)
    public String ioException() {
        return "error/500";
    }

}
