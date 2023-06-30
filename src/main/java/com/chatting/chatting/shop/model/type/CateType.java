package com.chatting.chatting.shop.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CateType {

    EAT("식품"),
    ELECTRONIC_PRODUCTS("전자제품"),
    NOTHING("잡템")
    ;

    private final String cate;
}
