package com.chatting.chatting.certification.repository;

import com.chatting.chatting.certification.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String userId);
    Optional<User> findByUserIdOrNickname(String userId, String nickname);
    Optional<User> findByEmail(String email);

}