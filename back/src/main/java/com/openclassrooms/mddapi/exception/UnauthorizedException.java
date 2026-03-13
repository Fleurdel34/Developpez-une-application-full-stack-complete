package com.openclassrooms.mddapi.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Create class UnauthorizedException
 * Execute exception processing
 */

@Getter
@Setter
public class UnauthorizedException extends Exception {

    private String messageException;
    private int code;

    public UnauthorizedException(String message) {
        super(message);
    }

    public String getMessageException() {
        return messageException= "Unauthorized";
    }

    public int getCode() {
        return code = 401;
    }
}
