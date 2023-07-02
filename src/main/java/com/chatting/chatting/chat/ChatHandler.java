package com.chatting.chatting.chat;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.model.type.UserRoleEnum;
import com.chatting.chatting.certification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Log4j2
@RequiredArgsConstructor
public class ChatHandler extends TextWebSocketHandler {

    private final UserRepository userRepository;
    private final List<WebSocketSession> agentSessions = new ArrayList<>();
    private final Map<String, WebSocketSession> userSessions = new ConcurrentHashMap<>();



    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        log.info("Received message: " + payload);

        User user = (User) session.getAttributes().get("user");

        if (user.getRole() == UserRoleEnum.USER) {
            // 유저가 보낸 메시지를 상담원에게 전송
            for (WebSocketSession agentSession : agentSessions) {
                agentSession.sendMessage(message);
            }
        } else if (user.getRole() == UserRoleEnum.ADMIN) {
            // 상담원이 보낸 메시지를 해당 유저에게 전송
            String targetUserId = (String) session.getAttributes().get("targetUserId");
            WebSocketSession userSession = userSessions.get(targetUserId);
            if (userSession != null) {
                userSession.sendMessage(message);
            }
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = session.getPrincipal().getName();
        Optional<User> user = userRepository.findByUserId(userId);
        session.getAttributes().put("user", user);

        if (user.get().getRole() == UserRoleEnum.USER) {
            // 유저 세션을 유저 리스트에 추가
            userSessions.put(userId, session);
        } else if (user.get().getRole() == UserRoleEnum.ADMIN) {
            // 상담원 세션을 상담원 리스트에 추가
            agentSessions.add(session);
        }

        log.info(session + " connected as " + user.get().getRole());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        User user = (User) session.getAttributes().get("user");

        if (user.getRole() == UserRoleEnum.USER) {
            // 유저 세션을 유저 리스트에서 제거
            userSessions.remove(user.getUserId());
        } else if (user.getRole() == UserRoleEnum.ADMIN) {
            // 상담원 세션을 상담원 리스트에서 제거
            agentSessions.remove(session);
        }

        log.info(session + " disconnected");
    }
}

