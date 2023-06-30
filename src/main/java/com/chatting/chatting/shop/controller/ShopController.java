package com.chatting.chatting.shop.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.shop.model.request.ShopCreateRequest;
import com.chatting.chatting.shop.model.response.ShopResponse;
import com.chatting.chatting.shop.service.ShopService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/shop")
public class ShopController {

    private final ShopService service;

    @PostMapping
    public ResponseJson<ShopResponse> save(
            @RequestPart ShopCreateRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestPart(value = "images", required = false) List<MultipartFile> images
    ) {
        ShopResponse response = service.createShop(request, userDetails.getUser(), images);
        return ResponseJson.success("성공적으로 상품을 만드셨습니다.", response);
    }

    @GetMapping
    public ResponseJson<String> test(
            @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseJson.success("권한",userDetails.getAuthorities().toString());
    }


}
