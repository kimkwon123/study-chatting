package com.chatting.chatting.shop.service;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.shop.model.entity.Shop;
import com.chatting.chatting.shop.model.entity.ShopHeart;
import com.chatting.chatting.shop.repository.ShopHeartRepository;
import com.chatting.chatting.shop.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ShopHeartService {

    private final ShopHeartRepository shopHeartRepository;
    private final ShopRepository shopRepository;

    @Transactional
    public String clickHeart(Long shopPid, User user) {
        Shop shop = shopRepository.findById(shopPid)
                .orElseThrow(() -> {
                    throw new CustomException(ErrorCode.NO_SHOP);
                });
        Optional<ShopHeart> shopHeart = shopHeartRepository.findByUserIdAndShop(user.getUserId(), shop);

        if (shopHeart.isEmpty()) {
            shopHeartRepository.save(ShopHeart.builder()
                    .shop(shop)
                    .userId(user.getUserId())
                    .build());
            return "성공적으로 좋아요 누르셨습니다.";
        }
        shopHeartRepository.deleteById(shopHeart.get().getPid());
        return "성공적으로 좋아요 취소 하였습니다.";
        

    }

}
