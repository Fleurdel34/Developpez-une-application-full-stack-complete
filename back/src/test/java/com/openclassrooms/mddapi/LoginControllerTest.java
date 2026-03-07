package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.controller.LoginController;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.security.JwtService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @InjectMocks
    private LoginController loginController;

    @Test
    void  loginTest() throws Exception {

        User user = new User();
        user.setUsername("julie");
        user.setEmail("julie@gmail.com");
        user.setPassword(passwordEncoder.encode("test1234"));
        userRepository.save(user);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "login": "julie@gmail.com",
                                  "password": "test1234"
                                }
                                """)
                )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void  loginFailedUnauthorizedTest() throws Exception {

        User user = new User();
        user.setUsername("juliette");
        user.setEmail("juliette@gmail.com");
        user.setPassword(passwordEncoder.encode("test1234"));
        userRepository.save(user);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "login": "juliette@gmail.com",
                                  "password": "test12"
                                }
                                """)
                )
                .andExpect(status().isUnauthorized());
    }
    @Test
    void  loginFailedBadRequestLoginNullTest() throws Exception {

        User user = new User();
        user.setUsername("amory");
        user.setEmail("amory@gmail.com");
        user.setPassword(passwordEncoder.encode("test1234"));
        userRepository.save(user);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "login": null,
                                  "password": "test12"
                                }
                                """)
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void  loginFailedBadRequestPasswordNullTest() throws Exception {

        User user = new User();
        user.setUsername("jonathan");
        user.setEmail("jonathan@gmail.com");
        user.setPassword(passwordEncoder.encode("test1234"));
        userRepository.save(user);

        mockMvc.perform(post("/api/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "login": "jonathan@gmail.com",
                                  "password": null
                                }
                                """)
                )
                .andExpect(status().isBadRequest());
    }


}
