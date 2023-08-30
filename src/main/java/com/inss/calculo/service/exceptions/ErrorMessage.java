package com.inss.calculo.service.exceptions;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
@Getter
@Setter
@Builder
public class ErrorMessage {
    private int statusCode;
    private OffsetDateTime timestamp;
    private String message;
    private String description;

    public ErrorMessage(int statusCode, OffsetDateTime timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

}
