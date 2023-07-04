package com.chatting.chatting.chat.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {

    JOIN("입장"),
    ENTER("채팅"),
    LEAVE("퇴장")
    ;
    
    private final String msgType;
}
