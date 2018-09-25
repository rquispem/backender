package com.glovoapp.backender.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.glovoapp.backender.exception.NoDescriptionsValuesException;

@ControllerAdvice
public class BaseControllerAdvice {

	@ExceptionHandler(NoDescriptionsValuesException.class)

    public String handledNotFoundException(NoDescriptionsValuesException e) {
        return "error";
    }
}
