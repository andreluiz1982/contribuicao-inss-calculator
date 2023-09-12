package com.inss.calculo.service.exceptions;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

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

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> RuntimeException(RuntimeException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage(
                HttpStatus.FAILED_DEPENDENCY.value(),
                OffsetDateTime.now(),
                ex.getMessage(),
                request.getDescription(false));

        return handleExceptionInternal(ex, message, null, HttpStatus.CONFLICT, request );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }


        ErrorMessage message = new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                OffsetDateTime.now(),
                "Erro de validação",
                errors.toString());

        return handleExceptionInternal(ex, message, null, HttpStatus.BAD_REQUEST, request );
    }



    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
        body =  body instanceof ErrorMessage ? body : body.toString();
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


}
