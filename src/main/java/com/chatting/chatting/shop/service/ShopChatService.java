package com.chatting.chatting.shop.service;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.repository.UserRepository;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.shop.model.entity.Shop;
import com.chatting.chatting.shop.model.entity.ShopRoom;
import com.chatting.chatting.shop.model.request.ShopChatRoomRequest;
import com.chatting.chatting.shop.repository.ShopRepository;
import com.chatting.chatting.shop.repository.ShopRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ShopChatService {

    private final ShopRoomRepository shopRoomRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public String createChatRoom(ShopChatRoomRequest request, User reqUser, Long ShopPid) {

        Shop shop = shopRepository.findById(ShopPid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_SHOP);
                });
        if (shop.getUser().getUserId().equals(request.sellUserId())) {
            long size = shopRoomRepository.findBySellUserId(shop.getUser().getUserId())
                    .size();
            System.out.println(size);
            UUID uid = UUID.randomUUID();
            shopRoomRepository.save(
                    ShopRoom.builder()
                            .buyUserId(reqUser.getUserId())
                            .sellUserId(shop.getUser().getUserId())
                            .shop(shop)
                            .roomKey(uid.toString())
                            .build()
            );
            return "성공적으로 채팅방을 개설하였습니다.";
        }
        throw new CustomException(ErrorCode.NO_SHOP_USER);
    }

    @Transactional
    public String deleteChatRoom(User user, String pid) {
        System.out.println(pid);
        ShopRoom shopRoom = shopRoomRepository.findByRoomKey(pid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_CHATROOM);
                });
        if (shopRoom.getBuyUserId().equals(user.getUserId()) ||
                shopRoom.getSellUserId().equals(user.getUserId())) {
            shopRoomRepository.deleteById(shopRoom.getPid());
            return "성공적으로 채팅방이 삭제되었습니다.";
        }
        throw new CustomException(ErrorCode.NO_SHOP_USER);
    }

}
