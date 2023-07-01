package com.chatting.chatting.inquiry.response;

import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.inquiry.type.InquiryCategory;


public record InquiryResponse(String title,
                              String content,
                              InquiryCategory category,
                              String imageUrl
//                              List<Reply> reply
) {

    public InquiryResponse(Inquiry inquiry) {
        this(
                inquiry.getTitle(),
                inquiry.getContent(),
                inquiry.getCategory(),
                inquiry.getImageUrl()
//                inquiry.getReply()
        );

    }
}