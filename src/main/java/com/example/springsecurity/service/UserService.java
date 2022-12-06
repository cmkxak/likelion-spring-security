package com.example.springsecurity.service;

import com.example.springsecurity.domain.User;
import com.example.springsecurity.domain.dto.UserJoinRequest;
import com.example.springsecurity.domain.dto.UserJoinResponse;
import com.example.springsecurity.domain.dto.UserLoginRequest;
import com.example.springsecurity.exception.ErrorCode;
import com.example.springsecurity.exception.UserNotFoundException;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.utils.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 100 * 60 * 60;

    public UserJoinResponse join(UserJoinRequest userJoinRequest) {
        //중복된 유저인지 검증
        userRepository.findByUserName(userJoinRequest.getUserName()).ifPresent(user -> {
            throw new UserNotFoundException(ErrorCode.DUPLICATED_USER_NAME, "이미 존재하는 유저입니다");
        });

        String rawPassword = encoder.encode(userJoinRequest.getPassword());
        // 중복된 유저가 아니라면, 유저를 DB에 저장
        User savedUser = userRepository.save(userJoinRequest.toEntity(rawPassword));

        return new UserJoinResponse(savedUser.getUserName(), savedUser.getEmail());
    }


    public String login(UserLoginRequest userLoginRequest) {
        //유저네임이랑 패스워드 입력받아서, 디비의 유저와 일치하는지
        User findUser = userRepository.findByUserName(userLoginRequest.getUserName()).orElseThrow(() ->
                new UserNotFoundException(ErrorCode.USER_NOT_FOUND, "유저가 존재하지 않습니다."));

        if(!encoder.matches(userLoginRequest.getPassword(), findUser.getPassword())){
            throw new UserNotFoundException(ErrorCode.INVALID_ID_PASSWORD, "비밀번호가 잘못되었습니다.");
        }
        return JwtTokenUtil.createToken(userLoginRequest.getUserName(), secretKey, expireTimeMs);
    }
}
