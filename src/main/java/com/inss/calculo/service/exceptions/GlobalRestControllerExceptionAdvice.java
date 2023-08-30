package com.inss.calculo.service.exceptions;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalRestControllerExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> resourceNotFoundException(ConstraintViolationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return handleExceptionInternal(ex, message, null, HttpStatus.BAD_REQUEST, request );
    }
    @ExceptionHandler(DuplicatedFieldException.class)
    public ResponseEntity<Object> resourceNotFoundException(DuplicatedFieldException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.CONFLICT.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return handleExceptionInternal(ex, message, null, HttpStatus.CONFLICT, request );
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
        body = (ErrorMessage) body;
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
