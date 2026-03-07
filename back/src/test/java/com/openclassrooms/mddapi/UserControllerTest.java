package com.openclassrooms.mddapi;


import com.openclassrooms.mddapi.model.User;

import com.openclassrooms.mddapi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "bernadette@gmail.com")
    void  getUserAuth() throws Exception {


        User userMock = new User();
        userMock.setUsername("bernadette");
        userMock.setEmail("bernadette@gmail.com");
        userMock.setPassword("test1234");
        User userSaved =userRepository.save(userMock);
        Long id = userSaved.getId();

        mockMvc.perform(get("/api/user/me")
                 .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.username").value("bernadette"))
                .andExpect(jsonPath("$.email").value("bernadette@gmail.com"));
    }

    @Test
    @WithMockUser(username = "marie@gmail.com")
    void  updateUser() throws Exception {

        User userMock = new User();
        userMock.setUsername("marie");
        userMock.setEmail("marie@gmail.com");
        userMock.setPassword("test1234");
        userRepository.save(userMock);

        String requestBody= """
               {
               "username":"mimi",
               "email": "marie@gmail.com",
               "password": "test1234"
               }
               """;

        mockMvc.perform(put("/api/user/me")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("update successful!"));

    }

}
