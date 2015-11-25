package com.ursideus.controllers;

import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

/**
 * Created by dovw on 11/25/15.
 */
public interface ExceptionAttributes {

    Map<String,Object> getExceptionAttributes(Exception exception, HttpServletRequest httpRequest, HttpStatus httpStatus);
}
