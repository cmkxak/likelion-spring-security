package com.example.springsecurity.controller;

import com.example.springsecurity.domain.dto.ReviewCreateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/reviews")
@Slf4j
@RestController
public class ReviewController {

    @PostMapping
    public ResponseEntity<String> write(@RequestBody ReviewCreateRequest reviewCreateRequest, Authentication authentication){
        log.info("isAuthenticated:{} name:{}", authentication.isAuthenticated(), authentication.getName());
        return ResponseEntity.ok().body(authentication.getName() + "님의 리뷰가 등록되었습니다.");
    }
}
