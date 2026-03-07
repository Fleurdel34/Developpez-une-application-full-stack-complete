package com.openclassrooms.mddapi.dto;


import java.util.Date;
import java.util.List;

/**
 * Create Record to recover of ArticleDTO
 * @param id,topic,title,content, created_at,userId and list comments
 */

public record ArticleDTO(
        Long id,
        String topic,
        String title,
        String content,
        Date created_at,
        Long userId,
        List<CommentDTO> comments
) {
}
