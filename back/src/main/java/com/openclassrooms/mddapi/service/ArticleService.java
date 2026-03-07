package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.model.Article;

import java.util.Map;

/**
 * Implements interface of service
 * implements business logic
 */

public interface ArticleService {
    void createArticle(Article article, String email);
    Map<String, Object> getArticlesAll();
    ArticleDTO getArticleById(Long id);
}
