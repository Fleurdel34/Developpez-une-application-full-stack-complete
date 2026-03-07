package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.service.Impl.ArticleServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    private final ArticleServiceImpl articleService;

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody Article article, Authentication authentication){
        String email = authentication.getName();
        articleService.createArticle(article, email);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getArticleAll(){
        Map<String, Object> articles = this.articleService.getArticlesAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<ArticleDTO> getArticleById(@PathVariable Long id){
        ArticleDTO articleDTO = this.articleService.getArticleById(id);
        return new ResponseEntity<>(articleDTO, HttpStatus.OK);
    }
}
