package com.chatting.chatting.chat.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.chat.model.request.ShopMessageRequest;
import com.chatting.chatting.chat.model.response.ShopMessageResponse;
import com.chatting.chatting.chat.service.ShopMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;

import java.net.http.HttpHeaders;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessageSendingOperations template;

    @MessageMapping("/chat-sendMessage")
    public void sendMessage(@Payload ShopMessageRequest request) {
        System.out.println(request);
        template.convertAndSend("/sub/" + request.roomKey(),
                new ShopMessageResponse(request.nickname(), request.msg()));
    }

}
