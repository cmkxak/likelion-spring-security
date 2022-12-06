package com.example.springsecurity.domain.dto;

import com.example.springsecurity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinRequest {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password) {
        return User.builder()
                .userName(this.userName)
                .password(password)
                .email(this.email)
                .build();
    }
}
