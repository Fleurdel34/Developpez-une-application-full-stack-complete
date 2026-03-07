package com.openclassrooms.mddapi.service.Impl;

import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.exception.UnauthorizedException;
import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Create class CommentServiceImpl
 * Execute business processing
 * Use the property CommentRepository and interface CommentService
 */

@AllArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;

    @SneakyThrows
    public void createComment(Long articleId, Comment comment, String email){

        Article article = articleRepository.findById(articleId)
                .orElseThrow(()-> new NotFoundException("article undefined"));
        comment.setArticle(article);

        User user = getUserByEmail(email);

        comment.setUser(user);

        Date dateTime = new Date();
        comment.setCreated_at(dateTime);

        if(comment.getId() == null){
            commentRepository.save(comment);
        }
    }

    public User getUserByEmail(String email) throws UnauthorizedException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UnauthorizedException("error"));
    }

}
