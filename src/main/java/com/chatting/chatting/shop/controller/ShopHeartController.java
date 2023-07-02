package com.chatting.chatting.shop.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.shop.service.ShopHeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shop/heart")
@RequiredArgsConstructor
public class ShopHeartController {

    private final ShopHeartService service;

    @PostMapping("/{id}")
    public ResponseJson<String> click(
            @PathVariable("id") Long shopPid,
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        System.out.println("eeeeeeeeeeeeeeee : "+userDetails.getUser());
        String msg = service.clickHeart(shopPid, userDetails.getUser());

        return ResponseJson.success("Success", msg);
    }

}
