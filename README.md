# spring-security-example

## 프로젝트 소개
이 프로젝트는 Spring Security를 활용하여 Authentication을 구현한 예제 프로젝트입니다.
Token을 발행하고, 만료되지 않았고 유효한 Token인 경우 인증되도록 합니다.

## Endpoints
|HTTP|URI| 설명 |
|:-----:|:------------------:|:-----------------------------:|
| POST | api/v1/users/join | 회원가입
| POST | api/v1/users/login | 로그인 및 토큰발급
| POST | api/v1/reviews | 토큰 인증

## 실행 방법
http://localhost:8080/reviews를 호출 할 때 Header의 Authorization에 
Bearer [/login에서 받은 Token]의 형식으로 넣어 POST 요청하면 됩니다.
