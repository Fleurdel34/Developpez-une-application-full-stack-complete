package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.Impl.CommentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class CommentController {

    private final CommentServiceImpl commentService;

    @PostMapping("/{articleId}/comments")
    public ResponseEntity<Void> createArticle(
            @PathVariable Long articleId,
            @RequestBody Comment comment,
            Authentication authentication
    ) {
        String email = authentication.getName();
        commentService.createComment(articleId, comment, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
