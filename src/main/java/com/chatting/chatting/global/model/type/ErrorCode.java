package com.chatting.chatting.global.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //회원가입
    SAME_NICKNAME(HttpStatus.BAD_REQUEST, "동일한 닉네임이 존재합니다."),
    WRONG_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰 값입니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
