package com.chatting.chatting.global.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //회원가입
    SAME_REGISTER(HttpStatus.BAD_REQUEST, "동일한 아이디나 닉네임이 존재합니다."),
    SAME_EMAIL(HttpStatus.BAD_REQUEST,"동일한 이메일로 가입을 하셨습니다."),
    NO_SUCCESSKEY(HttpStatus.BAD_REQUEST, "이메일 인증키가 맞지 않습니다."),
    NO_SUCCESS(HttpStatus.BAD_REQUEST, "이메일 인증 먼저 해주세요.")
    ;

    private final HttpStatus status;
    private final String message;
}
