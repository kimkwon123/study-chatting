package com.chatting.chatting.reply.controller;


import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.reply.request.RatingByUserRequest;
import com.chatting.chatting.reply.response.RatingByUserResponse;
import com.chatting.chatting.reply.service.RatingByUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reply/rating")
@RequiredArgsConstructor
public class RatingByUserController {

    private final RatingByUserService ratingByUserService;

    @PostMapping("/{replyId}")
    public ResponseJson<RatingByUserResponse> RatingToAgent(@PathVariable ("replyId") Long replyId,
                                                            @RequestBody RatingByUserRequest request,
                                                            @AuthenticationPrincipal UserDetailsImpl userDetails){

        RatingByUserResponse ratingByUserResponse = ratingByUserService.RatingToAgent(replyId,request ,userDetails);

        return ResponseJson.success("success", ratingByUserResponse);
    }
}
