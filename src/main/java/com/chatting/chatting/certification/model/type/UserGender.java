package com.chatting.chatting.certification.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserGender {

    MAN("남"),
    GIRL("여")
    ;

    private final String gender;

}
