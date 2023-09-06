package com.inss.calculo.service.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicatedFieldException extends DataIntegrityViolationException
{

    public DuplicatedFieldException(String message) {
        super(message);
    }
}
