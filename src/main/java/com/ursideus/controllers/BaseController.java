package com.ursideus.controllers;

import javassist.bytecode.ExceptionsAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by dovw on 11/24/15.
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception exception, HttpServletRequest request) {

        logger.error("> handleException()");
        logger.error(" Exception: ", exception);

        ExceptionAttributes exceptionsAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionsAttributes
                .getExceptionAttributes(exception, request, HttpStatus.INTERNAL_SERVER_ERROR);

        logger.error("< handleException()");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoResultException.class)
    public ResponseEntity<Map<String,Object>> handleNoResultException(Exception noResultException, HttpServletRequest request) {

        logger.info("> handleNoResultException()");

        ExceptionAttributes exceptionsAttributes = new DefaultExceptionAttributes();

        Map<String, Object> responseBody = exceptionsAttributes
                .getExceptionAttributes(noResultException, request, HttpStatus.INTERNAL_SERVER_ERROR);

        logger.info("< handleNoResultException()");
        return new ResponseEntity<Map<String, Object>>(responseBody, HttpStatus.NOT_FOUND);
    }
}
