package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.repository.TopicRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class TopicControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TopicRepository topicRepository;

    @Test
    @WithMockUser(username = "testuser")
    void getTopicAllTest() throws Exception {

        Topic topic3 = new Topic();
        topic3.setTitle("Third topic");
        topic3.setContent("very good topic");
        topicRepository.save(topic3);

        Topic topic4 = new Topic();
        topic4.setTitle("fourth topic");
        topic4.setContent("very interesting topic");
        topicRepository.save(topic4);


        mockMvc.perform(get("/api/topic")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.topics").isArray())
                .andExpect(jsonPath("$.topics.length()").value(4))
                .andExpect(jsonPath("$.topics[0].title").value("1er topic"))
                .andExpect(jsonPath("$.topics[1].title").value("Second topic"));

    }
}
