package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.service.Impl.ArticleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Article", description = "article management")
@AllArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @Operation(summary = "create article")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "create article, authorized with JWTToken"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody Article article, Authentication authentication){
        String email = authentication.getName();
        articleService.createArticle(article, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(summary = "get all articles")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "get all articles, authorized with JWTToken"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping
    public ResponseEntity<Map<String, Object>> getArticleAll(){
        Map<String, Object> articles = this.articleService.getArticlesAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @Operation(summary = "get one article")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "get one article, authorized with JWTToken"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping("{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id){
        ArticleDTO articleDTO = this.articleService.getArticleById(id);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }
}
