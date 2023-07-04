package com.chatting.chatting.chat.service;

import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.shop.model.entity.ShopRoom;
import com.chatting.chatting.chat.model.request.ShopMessageRequest;
import com.chatting.chatting.chat.model.response.ShopMessageResponse;
import com.chatting.chatting.shop.repository.ShopRoomRepository;
import com.chatting.chatting.global.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShopMessageService {

    private final ShopRoomRepository repository;
    private final JwtUtil jwtUtil;

    public boolean checkVaild(String token) {
        return jwtUtil.validateToken(token);
    }

    @Transactional
    public void checkRoom(String token, String roomKey) {
        String userId = jwtUtil.getUserInfoFromToken(token).getSubject();

        ShopRoom shopRoom = repository.findByRoomKey(roomKey)
                .orElseThrow(() -> {
                    System.out.println("ShopRoomKey 가 맞지 않다.");
                    throw new CustomException(ErrorCode.NO_CHATROOM);
                });
        System.out.println(userId);
        if (shopRoom.getSellUserId().equals(userId) ||
                shopRoom.getBuyUserId().equals(userId)) {
            return;
        }
        System.out.println("해당 유저가 아니다!");
        throw new CustomException(ErrorCode.NO_CHATROOM);
    }


}
