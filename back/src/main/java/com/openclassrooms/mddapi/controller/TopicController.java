package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.service.Impl.TopicServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/topic")
public class TopicController {

    private final TopicServiceImpl topicService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTopicAll(){
        Map<String, Object> topics = this.topicService.getTopicAll();
        return new ResponseEntity<>(topics, HttpStatus.OK);
    }
}
