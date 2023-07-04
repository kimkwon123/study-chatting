package com.chatting.chatting.shop.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.shop.model.request.ShopChatRoomRequest;
import com.chatting.chatting.shop.service.ShopChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop-chat")
public class ShopChatController {

    private final ShopChatService service;

    @PostMapping("/{id}")
    public ResponseJson<String> createChatRoom(@RequestBody ShopChatRoomRequest request,
                                               @PathVariable("id") Long id,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseJson.success("success", service.createChatRoom(request, userDetails.getUser(), id));
    }

    @DeleteMapping("/{id}")
    public ResponseJson<String> deleteChatRoom(@PathVariable("id") String id,
                                               @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseJson.success("success", service.deleteChatRoom(userDetails.getUser(), id));
    }

}
