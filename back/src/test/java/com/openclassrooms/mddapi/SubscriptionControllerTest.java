package com.openclassrooms.mddapi;



import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Test
    @WithMockUser(username = "john")
    void subscribeTest() throws Exception {

        Topic topic1 = new Topic();
        topic1.setTitle("1er topic");
        topic1.setContent("interesting topic");
        topicRepository.save(topic1);

        User user1 = new User();
        user1.setUsername("john");
        user1.setEmail("john@gmail.com");
        user1.setPassword("Toto1234@");
        userRepository.save(user1);

        mockMvc.perform(post("/api/subscription")
                .param("userId",user1.getId().toString())
                .param("topicId",topic1.getId().toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(username = "johan")
    void unsubscribeTest() throws Exception {

        Topic topic2 = new Topic();
        topic2.setTitle("Second topic");
        topic2.setContent("good topic");
        topicRepository.save(topic2);

        User user2 = new User();
        user2.setUsername("johan");
        user2.setEmail("johan@gmail.com");
        user2.setPassword("Toto1234@");
        userRepository.save(user2);

        Subscription subscription = new Subscription(new Date());
        subscription.setUser(user2);
        subscription.setTopic(topic2);
        subscriptionRepository.save(subscription);

        mockMvc.perform(delete("/api/subscription")
                        .param("userId",user2.getId().toString())
                        .param("topicId",topic2.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
}
