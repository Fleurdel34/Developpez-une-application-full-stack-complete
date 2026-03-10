package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.*;
import com.openclassrooms.mddapi.repository.ArticleRepository;

import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.Impl.ArticleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleServiceImpl articleService;

    @Test
    void createArticle(){

        User user1= new User();
        user1.setEmail("user1@gmail.com");

        Article article1 = new Article();
        article1.setUser(user1);
        article1.setTopic("test topic");
        article1.setTitle("test title");
        article1.setContent("interesting article");
        article1.setCreated_at(new Date());

        Mockito.when(userRepository.findByEmail("user1@gmail.com"))
                .thenReturn(Optional.of(user1));

        Mockito.when(articleRepository.save(article1))
                .thenReturn(article1);

        this.articleService.createArticle(article1, "user1@gmail.com");

        Mockito.verify(articleRepository).save(article1);
    }

    @Test
    void CreateArticleExist(){

        User user2= new User();
        user2.setEmail("user2@gmail.com");

        Article article2 = new Article();
        article2.setId(2L);

        Mockito.when(userRepository.findByEmail("user2@gmail.com"))
                .thenReturn(Optional.of(user2));


        articleService.createArticle(article2,"user2@gmail.com" );

        Mockito.verify(articleRepository, Mockito.never())
                .save(Mockito.any());
    }

    @Test
    void getArticleAllTest(){

        User user2= new User();
        user2.setId(2L);

        Article article2 = new Article();
        article2.setUser(user2);
        article2.setTopic("test topic");
        article2.setTitle("test title");
        article2.setContent("interesting article");
        article2.setCreated_at(new Date());
        article2.setComments(List.of());


        Article article3 = new Article();
        article3.setUser(user2);
        article3.setTopic("test topic");
        article3.setTitle("test title");
        article3.setContent("interesting article");
        article3.setCreated_at(new Date());
        article3.setComments(List.of());

        List<Article> articleList = java.util.List.of(article2, article3);

        Mockito.when(articleRepository.findAll())
                .thenReturn(articleList);

        this.articleService.getArticlesAll();

        Mockito.verify(articleRepository).findAll();

    }

    @Test
    void getArticleByIdTest(){

        User user5 =new User();
        user5.setUsername("toto");


        Article article3 = new Article();
        article3.setId(3L);
        article3.setUser(user5);
        article3.setComments(List.of());

        Mockito.when(articleRepository.findById(3L))
                .thenReturn(Optional.of(article3));

        this.articleService.getArticleById(3L);

        Mockito.verify(articleRepository).findById(3L);

    }
}
