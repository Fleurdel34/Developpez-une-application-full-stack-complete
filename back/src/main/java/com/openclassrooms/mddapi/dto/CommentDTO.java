package com.openclassrooms.mddapi.dto;

import java.util.Date;

/**
 * Create Record to recover of CommentDTO
 * @param id, content, created_at, username, articleId
 */

public record CommentDTO(
       Long id,
       String content,
       Date created_at,
       String username,
       Long articleId
) {
}
