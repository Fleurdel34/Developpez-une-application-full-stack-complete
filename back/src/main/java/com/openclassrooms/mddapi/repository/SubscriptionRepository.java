package com.openclassrooms.mddapi.repository;

import com.openclassrooms.mddapi.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Build Subscription
 * extends CrudRepository from tools Jpa
 * Use method existsByUserIdAndTopicId and findSubscriptionByUserIdAndTopicId
 */

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

   boolean existsByUserIdAndTopicId(Long userId, Long topicId);

   Optional<Subscription> findSubscriptionByUserIdAndTopicId(Long userId, Long topic);


}
