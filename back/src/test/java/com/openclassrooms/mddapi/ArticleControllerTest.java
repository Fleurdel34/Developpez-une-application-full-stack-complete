package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.Article;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.ArticleRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ArticleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @WithMockUser(username="nata@gmail.com")
    public void createArticleTest() throws Exception{

        User userNata= new User();
        userNata.setUsername("nata");
        userNata.setEmail("nata@gmail.com");
        userNata.setPassword("Toto1234@");
        userRepository.save(userNata);


        String requestBody= """
               {
               "topic": "topic article",
               "title":"title article",
               "content": "test comment"
               }
               """;


        mockMvc.perform(post("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "rose@gmail.com")
    void getArticleAllTest() throws Exception {

        User userRose= new User();
        userRose.setUsername("rose");
        userRose.setEmail("rose@gmail.com");
        userRose.setPassword("Toto1234@");
        userRepository.save(userRose);

        Article article4 = new Article();
        article4.setTopic("topic4 article");
        article4.setTitle("good topic");
        article4.setContent("good article");
        article4.setUser(userRose);
        articleRepository.save(article4);

        Article article5 = new Article();
        article5.setTopic("topic5 article");
        article5.setTitle("very good topic");
        article5.setContent("very good topic");
        article5.setUser(userRose);
        articleRepository.save(article5);


        mockMvc.perform(get("/api/articles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.articles").isArray())
                .andExpect(jsonPath("$.articles.length()").value(3))
                .andExpect(jsonPath("$.articles[0].topic").value("topic article"))
                .andExpect(jsonPath("$.articles[1].topic").value("topic4 article"));


    }
}
