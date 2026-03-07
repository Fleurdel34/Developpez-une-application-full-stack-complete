package com.openclassrooms.mddapi.dto;

/**
 * Create Record to recover of AuthenticationDTO
 * @param login, password
 */

public record AuthenticationDTO(
        String login,
        String password
) {
}
