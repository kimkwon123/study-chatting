package com.chatting.chatting.reply.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.inquiry.response.InquiryResponse;
import com.chatting.chatting.reply.request.ReplyRequest;
import com.chatting.chatting.reply.response.ReplyResponse;
import com.chatting.chatting.reply.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply")
@RequiredArgsConstructor
public class ReplyController {
    private final ReplyService replyService;
    @PostMapping("/{inquiryId}")
    public ResponseJson<String> createReply(@PathVariable("inquiryId") Long inquiryId,
                                            @RequestBody ReplyRequest request,
                                            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // PostRequestDto를 사용하여 게시글을 생성하고, 생성된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        String msg = replyService.createReply(inquiryId,request, userDetails);

        return ResponseJson.success("success", msg);
    }

    @PutMapping("/{replyId}")
    public ResponseJson<ReplyResponse> updateInquiry(
            @PathVariable("replyId") Long replyId,
            @RequestBody InquiryRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        ReplyResponse replyResponse = replyService.updateReply(replyId, request, userDetails);

        return ResponseJson.success("success", replyResponse);
    }

    @DeleteMapping("/{replyId}")
    public ResponseJson<String> deleteInquiry(
            @PathVariable("replyId") Long replyId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String agentId = replyService.deleteReply(replyId, userDetails);

        return ResponseJson.success("success","[" + agentId + "} 가 작성한 문의가 삭제 되었습니다.");
    }
}
