package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username="hawa@gmail.com")
    public void createCommentTest() throws Exception{

        User user1= new User();
        user1.setUsername("hawa");
        user1.setEmail("hawa@gmail.com");
        user1.setPassword("Toto1234@");
        userRepository.save(user1);

        Article article1 = new Article();
        article1.setUser(user1);
        article1.setTopic("test topic");
        article1.setTitle("test title");
        article1.setContent("interesting article");
        article1.setCreated_at(new Date());
        articleRepository.save(article1);

        String requestBody= """
               {
               "email": "hawa@gmail.com",
               "content": "test comment"
               }
               """;


        mockMvc.perform(post("/api/articles/1/comments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }
}
