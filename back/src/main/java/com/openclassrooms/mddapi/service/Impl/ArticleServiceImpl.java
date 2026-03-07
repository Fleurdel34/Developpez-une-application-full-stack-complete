package com.openclassrooms.mddapi.service.Impl;


import com.openclassrooms.mddapi.dto.ArticleDTO;
import com.openclassrooms.mddapi.dto.CommentDTO;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.ArticleService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Create class ArticleServiceImpl
 * Execute business processing
 * Use the property ArticleRepository and interface ArticleService
 */

@AllArgsConstructor
@Service
public class ArticleServiceImpl implements ArticleService {

    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    @SneakyThrows
    public void createArticle(Article article, String email){

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("error"));

        article.setUser(user);
        Date dateTime = new Date();
        article.setCreated_at(dateTime);

        if(article.getId() == null){
            articleRepository.save(article);
        }
    }

    public Map<String, Object> getArticlesAll(){

        List<ArticleDTO> articles = this.articleRepository.findAll().stream()
                .map(article -> new ArticleDTO(
                        article.getId(),
                        article.getTopic(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreated_at(),
                        article.getUser().getId(),
                        article.getComments().stream()
                                .map(comment -> new CommentDTO(
                                        comment.getId(),
                                        comment.getContent(),
                                        comment.getCreated_at(),
                                        comment.getUser().getId(),
                                        comment.getArticle().getId()
                                )).toList()
                )).toList();
        Map<String, Object> response = new HashMap<>();
        response.put("articles", articles);
        return response;
    }

    public ArticleDTO getArticleById(Long id) {
        return articleRepository.findById(id)
                .map(article -> new ArticleDTO(
                        article.getId(),
                        article.getTopic(),
                        article.getTitle(),
                        article.getContent(),
                        article.getCreated_at(),
                        article.getUser().getId(),
                        article.getComments().stream()
                                .map(comment -> new CommentDTO(
                                        comment.getId(),
                                        comment.getContent(),
                                        comment.getCreated_at(),
                                        comment.getUser().getId(),
                                        comment.getArticle().getId()
                                )).toList()
                )).orElse(null);
    }
}
