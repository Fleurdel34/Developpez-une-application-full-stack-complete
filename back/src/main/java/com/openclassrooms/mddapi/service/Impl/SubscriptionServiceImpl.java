package com.openclassrooms.mddapi.service.Impl;

import com.openclassrooms.mddapi.exception.BadRequestException;
import com.openclassrooms.mddapi.exception.NotFoundException;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.model.User;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.repository.UserRepository;
import com.openclassrooms.mddapi.service.SubscriptionService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Date;



/**
 * Create class SubscriptionServiceImpl
 * Execute business processing
 * Use the property SubscriptionRepository and interface SubscriptionService
 */

@AllArgsConstructor
@Service
public class SubscriptionServiceImpl implements SubscriptionService {


    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;

    @SneakyThrows
    public Subscription subscribe(Long userId, Long topicId){

        if(subscriptionRepository.existsByUserIdAndTopicId(userId, topicId)){
            throw new BadRequestException("Already Subscribe");
        }

        User user = userRepository.findById(userId).orElseThrow(() ->new NotFoundException("User not found"));

        Topic topic = topicRepository.findById(topicId).orElseThrow(() ->new NotFoundException("Topic not found"));

        Subscription subscription= new Subscription();
        subscription.setCreated_at(new Date());
        subscription.setUser(user);
        subscription.setTopic(topic);

        return subscriptionRepository.save(subscription);

    }

    @SneakyThrows
    public void unsubscribe (Long userId, Long topicId){

        Subscription subscription = subscriptionRepository.findSubscriptionByUserIdAndTopicId(userId, topicId)
                .orElseThrow(() ->new NotFoundException("Subscription not found"));

        subscriptionRepository.delete(subscription);

    }

}
