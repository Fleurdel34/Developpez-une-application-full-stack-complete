package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.Impl.TopicServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class TopicServiceImplTest {

   @Mock
   private TopicRepository topicRepository;

    @InjectMocks
    private TopicServiceImpl topicService;

    @Test
    void getTopicAllTest(){

        User user1= new User();
        user1.setId(1L);

        Subscription subscription1 = new Subscription();
        subscription1.setUser(user1);
        subscription1.setCreated_at(new Date());

        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setTitle("1er topic");
        topic1.setContent("interesting topic");
        topic1.setSubscriptions(List.of(subscription1));

        Topic topic2 = new Topic();
        topic2.setId(2L);
        topic2.setTitle("Second topic");
        topic2.setContent("good topic");
        topic2.setSubscriptions(List.of(subscription1));

        List<Topic> topicList = java.util.List.of(topic1, topic2);

        Mockito.when(topicRepository.findAll())
                .thenReturn(topicList);

        this.topicService.getTopicAll();
        Mockito.verify(topicRepository).findAll();

    }
}
