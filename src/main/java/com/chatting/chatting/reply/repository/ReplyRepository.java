package com.chatting.chatting.reply.repository;

import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.reply.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Long> {
}
