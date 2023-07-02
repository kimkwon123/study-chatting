package com.chatting.chatting.shop.repository;

import com.chatting.chatting.shop.model.entity.Shop;
import com.chatting.chatting.shop.model.entity.ShopHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopHeartRepository extends JpaRepository<ShopHeart, Long> {

    Optional<ShopHeart> findByUserIdAndShop(String userId, Shop shop);

}