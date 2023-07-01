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
    NO_INQUIRY_TITLE(HttpStatus.BAD_REQUEST, "제목을 입력해주세요"),
    NO_INQUIRY_CONTENT(HttpStatus.BAD_REQUEST, "내용을 입력해주세요"),
    NO_INQUIRY_CATEGORY(HttpStatus.BAD_REQUEST, "문의 카테고리를 선택하세요"),
    NO_AUTHORITY_TO_INQUIRY(HttpStatus.BAD_REQUEST, "문의에 대한 수정/삭제 권한이 없습니다."),
    NO_INQUIRY(HttpStatus.BAD_REQUEST, "존재하지 않는 문의글 입니다."),
    NO_REPLY_CONTENT(HttpStatus.BAD_REQUEST, "내용을 입력하세요"),
    NO_ADMIN(HttpStatus.BAD_REQUEST, "당신은 관리자가 아닙니다."),
    NO_REPLY(HttpStatus.BAD_REQUEST, "문의에 대한 답변이 없습니다."),
    NO_AUTHORITY_TO_REPLY(HttpStatus.BAD_REQUEST, "답변에 대한 수정/삭제 권한이 없습니다.")
    ;

    private final HttpStatus status;
    private final String message;
}
