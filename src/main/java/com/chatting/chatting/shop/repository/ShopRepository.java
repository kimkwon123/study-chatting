package com.chatting.chatting.shop.repository;

import com.chatting.chatting.shop.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopRepository extends JpaRepository<Shop, Long> {
}