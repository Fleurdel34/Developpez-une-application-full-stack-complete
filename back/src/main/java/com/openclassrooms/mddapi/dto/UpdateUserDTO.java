package com.openclassrooms.mddapi.dto;

/**
 * Create Record to recover of User
 * @param username, email, password
 */

public record UpdateUserDTO(
        String username,
        String email,
        String password
) {
}
