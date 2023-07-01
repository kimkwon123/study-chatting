package com.chatting.chatting.inquiry.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryCategory {

    ACCOUNT("계정"),
    CHAT("채팅"),
    TRANSACTION("거래");
    ;

    private final String displayName;
}
