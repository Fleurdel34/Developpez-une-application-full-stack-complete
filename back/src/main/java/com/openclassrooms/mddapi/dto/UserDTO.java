package com.openclassrooms.mddapi.dto;

/**
 * Create Record to recover of User
 * @param id, username, email,
 */

public record UserDTO(
    Long id,
    String username,
    String email
) {
}
