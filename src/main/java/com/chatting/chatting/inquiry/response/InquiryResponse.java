package com.chatting.chatting.inquiry.response;

import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.inquiry.type.InquiryCategory;
import com.chatting.chatting.reply.entity.Reply;
import org.hibernate.Hibernate;

import java.util.List;


public record InquiryResponse(
        String title,
        String content,
        InquiryCategory category,
        String imageUrl

) {

    public InquiryResponse(Inquiry inquiry) {
        this(
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCategory(),
                inquiry.getImageUrl()
        );
    }
}
