package com.openclassrooms.mddapi.dto;

import java.util.List;

/**
 * Create Record to recover of Topic
 * @param id, title, content and List Subscription
 */

public record TopicDTO(
        Long id,
        String title,
        String content,
        List<SubscriptionDTO> subscriptions
) {
}
