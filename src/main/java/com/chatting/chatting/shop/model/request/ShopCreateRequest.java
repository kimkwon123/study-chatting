package com.chatting.chatting.shop.model.request;

public record ShopCreateRequest(
        String shopName,
        String shopContent,
        Integer money,
        boolean auction,
        String cate
){

}
