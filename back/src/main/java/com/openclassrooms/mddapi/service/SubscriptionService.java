package com.openclassrooms.mddapi.service;

import com.openclassrooms.mddapi.model.Subscription;

/**
 * Implements interface of service
 * implements business logic
 */

public interface SubscriptionService {

    Subscription subscribe(Long userId, Long topicId);
    void unsubscribe (Long userId, Long topicId);
}
