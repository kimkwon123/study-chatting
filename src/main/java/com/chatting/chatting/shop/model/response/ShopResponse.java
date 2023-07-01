package com.chatting.chatting.shop.model.response;

import com.chatting.chatting.shop.model.entity.Shop;

import java.time.LocalDateTime;

public record ShopResponse(
        String shopName,
        String userName,
        String shopContent,
        boolean auction,
        String progressive,
        Integer money,
        String cate,
        LocalDateTime createDate
) {

    public static ShopResponse getShopCreateRequest(Shop shop){
        return new ShopResponse(
                shop.getShopName(),
                shop.getUser().getNickname(),
                shop.getShopContent(),
                shop.getAuction(),
                shop.getProgressive().getStatus(),
                shop.getMoney(),
                shop.getCate().getCate(),
                shop.getCreatedAt()
        );
    }

}
