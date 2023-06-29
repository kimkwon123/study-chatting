package com.chatting.chatting.certification.model.request;

public record RegisterRequest(
        String userId,
        String userPw,
        String nickname,
        String gender,
        String email,
        String successKey
) {
}
