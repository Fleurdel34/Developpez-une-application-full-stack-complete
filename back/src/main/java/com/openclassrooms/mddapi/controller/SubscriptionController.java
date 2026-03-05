package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.service.Impl.SubscriptionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionService;

    @PostMapping
    public ResponseEntity<Void> subscribe(@RequestParam Long userId, @RequestParam Long topicId){
        subscriptionService.subscribe(userId, topicId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> unsubscribe(@RequestParam Long userId, @RequestParam Long topicId){
        subscriptionService.unsubscribe(userId, topicId);
        return ResponseEntity.noContent().build();
    }

}
