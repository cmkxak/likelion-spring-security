package com.example.springsecurity.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "유저가 존재하지 않습니다."),
    DUPLICATED_USER_NAME(HttpStatus.CONFLICT, "중복되는 유저 입니다."),
    INVALID_ID_PASSWORD(HttpStatus.BAD_REQUEST, "아이디 혹은 비밀번호가 올바르지 않습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "내부 서버에서 에러가 발생했습니다.");

    private HttpStatus httpStatus;
    private String messgae;
}
