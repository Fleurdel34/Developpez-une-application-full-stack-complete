package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.service.Impl.CommentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Comment", description = "comment management")
@AllArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class CommentController {

    private final CommentServiceImpl commentService;

    @Operation(summary = "create comment")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "create comment, authorized with JWTToken"),
    @ApiResponse(responseCode = "404", description = "Not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping("/{articleId}/comments")
    public ResponseEntity<Void> createComment(
            @PathVariable Long articleId,
            @RequestBody Comment comment,
            Authentication authentication
    ) {
        String email = authentication.getName();
        commentService.createComment(articleId, comment, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
