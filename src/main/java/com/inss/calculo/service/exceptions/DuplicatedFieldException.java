package com.inss.calculo.service.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DuplicatedFieldException extends DataIntegrityViolationException
{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatedFieldException(String message) {
        super(message);
    }
}
