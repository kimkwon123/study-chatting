package com.chatting.chatting.inquiry.request;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.inquiry.type.InquiryCategory;
import lombok.Getter;


public record InquiryRequest(
         String title,
         String content,
         InquiryCategory category,
         String ImageUrl
 ) {

}
