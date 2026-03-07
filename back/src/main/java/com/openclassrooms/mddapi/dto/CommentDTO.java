package com.openclassrooms.mddapi.dto;

import java.util.Date;

/**
 * Create Record to recover of CommentDTO
 * @param id, content, created_at, userId, articleId
 */

public record CommentDTO(
       Long id,
       String content,
       Date created_at,
       Long userId,
       Long articleId
) {
}
