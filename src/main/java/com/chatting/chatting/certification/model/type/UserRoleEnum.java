package com.chatting.chatting.certification.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ADMIN("admin"),
    AGENT("agent")
    ;

    private final String role;

}