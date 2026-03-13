package com.openclassrooms.mddapi.dto;

import java.util.Date;

/**
 * Create Record to recover of Subscription
 * @param userId, created_at
 */

public record SubscriptionDTO(
        Long userId,
        Date created_at
) {
}
