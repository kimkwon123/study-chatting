package com.chatting.chatting.chat.intercepter;

import com.chatting.chatting.chat.model.request.ShopMessageRequest;
import com.chatting.chatting.chat.service.ShopMessageService;
import com.chatting.chatting.global.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebScoketInterceptor implements ChannelInterceptor {

    private final ShopMessageService service;

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {

        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        MessageHeaders headers = message.getHeaders(); //메세지 들어올때 헤더
        MultiValueMap<String, String> map = headers.get(StompHeaderAccessor.NATIVE_HEADERS, MultiValueMap.class);

        //접속할때만 헤더에 roomKey 를 담고 그다음 메세지 줄때는 roomKey 를 가지고 통신.
        if (StompCommand.CONNECT == accessor.getCommand()) {
            String token = map.get("Authorization").get(0);
            System.out.println("접속함.");
            System.out.println("접속함 : "+token);
            if(service.checkVaild(token)){
                service.checkRoom(map.get("Authorization").get(0), map.get("roomKey").get(0));
            }
        }
        return message;
    }

}
