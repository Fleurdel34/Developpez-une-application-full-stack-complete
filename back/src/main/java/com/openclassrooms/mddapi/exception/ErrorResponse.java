package com.openclassrooms.mddapi.exception;

import lombok.Data;

import java.util.List;

/**
 * Create class ErrorResponse
 * Execute exception processing
 * add more messages
 */

@Data
public class ErrorResponse {
    private List<String> messageDetails;
}
