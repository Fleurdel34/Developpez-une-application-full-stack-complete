package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.service.Impl.SubscriptionServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Subscription", description = "subscription management")
@RestController
@RequestMapping("/api/subscription")
@AllArgsConstructor
public class SubscriptionController {

    private final SubscriptionServiceImpl subscriptionService;

    @Operation(summary = "subscribe")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "subscribe, authorized with JWTToken"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PostMapping
    public ResponseEntity<Void> subscribe(@RequestParam Long userId, @RequestParam Long topicId){
        subscriptionService.subscribe(userId, topicId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "unsubscribe")
    @ApiResponses({ @ApiResponse(responseCode = "204", description = "unsubscribe, authorized with JWTToken"),
    @ApiResponse(responseCode = "404", description = "Not found"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @DeleteMapping
    public ResponseEntity<Void> unsubscribe(@RequestParam Long userId, @RequestParam Long topicId){
        subscriptionService.unsubscribe(userId, topicId);
        return ResponseEntity.noContent().build();
    }

}
