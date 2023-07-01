package com.chatting.chatting.reply.entity;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.reply.request.ReplyRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;
    @Column
    private String content;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private User user;

    public Reply(ReplyRequest request, Inquiry inquiry, UserDetailsImpl userDetails) {
        this.content = request.content();
        this.inquiry = inquiry;
        this.user = userDetails.getUser();
    }

    public void update(InquiryRequest request) {
        this.content = request.content();
    }
}