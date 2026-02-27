package com.openclassrooms.mddapi.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Create class BadRequestException
 * Execute exception processing
 */

@Setter
@Getter
public class BadRequestException extends Exception {

    private String messageException;
    private int code;

    public BadRequestException(String message) {
        super(message);
    }

    public String getMessageException() {
        return messageException= "Bad Request";
    }

    public int getCode() {
        return code = 400;
    }
}