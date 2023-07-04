package com.chatting.chatting.shop.repository;

import com.chatting.chatting.shop.model.entity.ShopRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ShopRoomRepository extends JpaRepository<ShopRoom, Long> {

    Optional<ShopRoom> findByRoomKey(String roomKey);

    List<ShopRoom> findBySellUserId(String sellUserId);

}