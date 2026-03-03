package com.openclassrooms.mddapi.service.Impl;

import com.openclassrooms.mddapi.dto.SubscriptionDTO;
import com.openclassrooms.mddapi.dto.TopicDTO;
import com.openclassrooms.mddapi.repository.TopicRepository;
import com.openclassrooms.mddapi.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * Create class TopicServiceImpl
 * Execute business processing
 * Use the property TopicRepository and interface TopicService
 */

@AllArgsConstructor
@Service
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;

    public Map<String, Object> getTopicAll() {

        List<TopicDTO> topics = this.topicRepository.findAll().stream()
                .map(topic -> new TopicDTO(
                        topic.getId(),
                        topic.getTitle(),
                        topic.getContent(),
                        topic.getSubscriptions().stream()
                                .map(subscription -> new SubscriptionDTO(
                                        subscription.getUser().getId(),
                                        subscription.getCreated_at()
                )).toList()))
                    .toList();

        Map<String, Object> response = new HashMap<>();
        response.put("topics", topics);

        return response;
    }
}
