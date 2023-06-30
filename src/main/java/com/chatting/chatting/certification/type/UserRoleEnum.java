package com.chatting.chatting.certification.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

    USER("user"),
    ADMIN("admin")
    ;

    private final String role;

}