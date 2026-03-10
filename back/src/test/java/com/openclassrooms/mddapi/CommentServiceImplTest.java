package com.openclassrooms.mddapi;


import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.Comment;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.CommentRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.Impl.CommentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    private  UserRepository userRepository;

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void createComment(){

        Article article1 = new Article();
        article1.setId(1L);

        User user1= new User();
        user1.setEmail("user1@gmail.com");

        Comment comment1 = new Comment();
        comment1.setArticle(article1);
        comment1.setUser(user1);
        comment1.setContent("interesting article");
        comment1.setCreated_at(new Date());

        Mockito.when(userRepository.findByEmail("user1@gmail.com"))
                .thenReturn(Optional.of(user1));

        Mockito.when(articleRepository.findById(1L))
                .thenReturn(Optional.of(article1));

        Mockito.when(commentRepository.save(comment1))
                .thenReturn(comment1);

        this.commentService.createComment(1L, comment1, "user1@gmail.com");

        Mockito.verify(commentRepository).save(comment1);
    }

    @Test
    void CreateCommentExist(){

        Article article2 = new Article();
        article2.setId(2L);

        User user2= new User();
        user2.setEmail("user2@gmail.com");

        Comment comment2 = new Comment();
        comment2.setId(2L);

        Mockito.when(userRepository.findByEmail("user2@gmail.com"))
                .thenReturn(Optional.of(user2));

        Mockito.when(articleRepository.findById(2L))
                .thenReturn(Optional.of(article2));

       commentService.createComment(2L, comment2,"user2@gmail.com" );

        Mockito.verify(commentRepository, Mockito.never())
                .save(Mockito.any());

    }
}
