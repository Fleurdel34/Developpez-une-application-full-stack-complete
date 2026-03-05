package com.openclassrooms.mddapi;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.Impl.SubscriptionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class SubscriptionServiceImplTest {

    @Mock
    private  SubscriptionRepository subscriptionRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TopicRepository topicRepository;

    @InjectMocks
    private SubscriptionServiceImpl subscriptionService;

    @Test
    void subscribeTest(){

        User user1= new User();
        user1.setId(1L);

        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setTitle("1er topic");
        topic1.setContent("interesting topic");


        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user1));
        Mockito.when(topicRepository.findById(1L)).thenReturn(Optional.of(topic1));


        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class)))
                .thenReturn(new Subscription());

        this.subscriptionService.subscribe(user1.getId(), topic1.getId());

        Mockito.verify(subscriptionRepository).save(Mockito.argThat(sub ->
                sub.getUser().getId().equals(1L) && sub.getTopic().getId().equals(1L)
        ));
    }

    @Test
    void subscribeFailedTest(){

        User user1= new User();
        user1.setId(1L);

        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setTitle("1er topic");
        topic1.setContent("interesting topic");

        Mockito.when(subscriptionRepository.existsByUserIdAndTopicId(1L, 1L))
                .thenReturn(true);

        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            subscriptionService.subscribe(1L, 1L);
        });

        assertThat(exception.getMessage()).isEqualTo("Already Subscribe");

        Mockito.verify(subscriptionRepository, Mockito.never())
                .save(Mockito.any());
    }

    @Test
    void unsubscribeTest(){

        User user1= new User();
        user1.setId(1L);

        Topic topic1 = new Topic();
        topic1.setId(1L);
        topic1.setTitle("1er topic");
        topic1.setContent("interesting topic");

        Subscription subscription1 = new Subscription();
        subscription1.setUser(user1);
        subscription1.setCreated_at(new Date());

        Mockito.when(subscriptionRepository.findSubscriptionByUserIdAndTopicId(1L, 1L))
                .thenReturn(Optional.of(subscription1));

        this.subscriptionService.unsubscribe(user1.getId(), topic1.getId());

        Mockito.verify(subscriptionRepository).delete(subscription1);

    }

}
