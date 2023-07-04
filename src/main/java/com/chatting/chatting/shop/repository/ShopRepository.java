package com.chatting.chatting.shop.repository;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.shop.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}