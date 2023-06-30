package com.chatting.chatting.shop.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProgressiveType {

    WAIT("판매중"),
    SELL("판매완료")
    ;

    private final String status;

}
