package com.chatting.chatting.chat.model.request;


import com.chatting.chatting.chat.model.type.MessageType;
import lombok.ToString;

public record ShopMessageRequest(
        String roomKey,
        String msg,
        MessageType type,
        String nickname
) {

    public String toString(){
        return roomKey+" : "+msg+" : "+type;
    }

}
