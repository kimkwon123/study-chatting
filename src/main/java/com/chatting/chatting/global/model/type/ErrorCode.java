package com.chatting.chatting.global.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    //회원가입
    SAME_NICKNAME(HttpStatus.BAD_REQUEST, "동일한 닉네임이 존재합니다."),
    SAME_REGISTER(HttpStatus.BAD_REQUEST, "동일한 아이디나 닉네임이 존재합니다."),
    SAME_EMAIL(HttpStatus.BAD_REQUEST,"동일한 이메일로 가입을 하셨습니다."),
    NO_SUCCESSKEY(HttpStatus.BAD_REQUEST, "이메일 인증키가 맞지 않습니다."),
    NO_SUCCESS(HttpStatus.BAD_REQUEST, "이메일 인증 먼저 해주세요."),

    //로그인
    WRONG_TOKEN(HttpStatus.BAD_REQUEST, "잘못된 토큰 값입니다."),

    //상품
    WRONG_SHOP_USER(HttpStatus.UNAUTHORIZED, "해당 상품은 작성자만 수정/삭제 할 수 있습니다."),
    NO_SHOP(HttpStatus.BAD_REQUEST, "잘못된 상품 접근 입니다."),

    //파일
    ERROR_FILE(HttpStatus.NOT_FOUND, "해당 파일을 삭제하지 못하였습니다."),
    NO_FILE(HttpStatus.NOT_FOUND, "해당 파일을 찾을수 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
