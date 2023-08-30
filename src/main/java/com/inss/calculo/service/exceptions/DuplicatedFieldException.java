package com.inss.calculo.service.exceptions;

public class DuplicatedFieldException extends RuntimeException {
    public DuplicatedFieldException() {
    }

    public DuplicatedFieldException(String message) {
        super(message);
    }
}
