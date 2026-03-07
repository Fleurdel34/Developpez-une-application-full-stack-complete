package com.openclassrooms.mddapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class RegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void registerUserTest() throws Exception{

        String requestBody= """
               {
               "username":"hawa",
               "email": "hawa@gmail.com",
               "password": "Hawa1234@"
               }
               """;


        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("registration successful!"));
    }

    @Test
    public void registerUserBadRequestTest() throws Exception{

        String requestBody= """
               {
               "username":"fabien"
               "email": "fabiengmail.com",
               "password": "Julie1234@"
               }
               """;


        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

}
