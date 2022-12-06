package com.example.springsecurity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewCreateRequest {
    private String userName;
    private String title;
    private String content;
}
