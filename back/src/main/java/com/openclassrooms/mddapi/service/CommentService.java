package com.openclassrooms.mddapi.service;


import com.openclassrooms.mddapi.model.Comment;

/**
 * Implements interface of service
 * implements business logic
 */

public interface CommentService {
    void createComment(Long articleId, Comment comment, String email);
}
