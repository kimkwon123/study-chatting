package com.chatting.chatting.reply.response;

import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.reply.entity.Reply;

public record ReplyResponse(
        String content
) {
    public ReplyResponse(Reply reply) {
        this(
                reply.getContent()
        );
    }
}
