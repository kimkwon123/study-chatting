package com.chatting.chatting.reply.repository;

import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    Optional<Reply> findAllByInquiry(Inquiry inquiry);
}
