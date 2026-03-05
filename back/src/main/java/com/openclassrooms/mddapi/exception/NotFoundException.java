package com.openclassrooms.mddapi.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * Create class NotFoundRequestException
 * Execute exception processing
 */

@Setter
@Getter
public class NotFoundException extends Exception {

    private String messageException;
    private int code;

    public NotFoundException(String message) {
        super(message);
    }

    public String getMessageException() {
        return messageException= "Not found";
    }

    public int getCode() {
        return code = 404;
    }
}
