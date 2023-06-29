package com.chatting.chatting.certification.repository;

import com.chatting.chatting.certification.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}