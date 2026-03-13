package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.service.Impl.TopicServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(name = "Topic", description = "topic management")
@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicServiceImpl topicService;

    @Operation(summary = "findAll  topics")
    @ApiResponses({ @ApiResponse(responseCode = "200", description = "find all topics, authorized with JWTToken"),
    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @GetMapping
    public ResponseEntity<Map<String, Object>> getTopicAll(){
        Map<String, Object> topics = this.topicService.getTopicAll();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
}
