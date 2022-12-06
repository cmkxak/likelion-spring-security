package com.example.springsecurity.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Response<T> {
    String resultCode;
    T result;

    public static Response<Void> error(String resultCode){
        return new Response(resultCode, null);
    }

    public static<T> Response<T> success(T result){
        return new Response("SUCCESS", result);
    }
}
