package com.chatting.chatting.inquiry.controller;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.model.response.ResponseJson;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.inquiry.response.InquiryResponse;
import com.chatting.chatting.inquiry.service.InquiryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/inquiry")
@RequiredArgsConstructor
public class InquiryController {
    private final InquiryService inquiryService;

    @PostMapping("")
    public ResponseJson<String> createInquiry(@RequestBody InquiryRequest request, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        // PostRequestDto를 사용하여 게시글을 생성하고, 생성된 게시글의 정보를 PostResponseDto에 담아 반환합니다.
        String msg = inquiryService.createInquiry(request, userDetails);

        return ResponseJson.success("success", msg);
    }

    @GetMapping("")
    public ResponseJson<List<InquiryResponse>> getAllInquiry(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        List<InquiryResponse> responseDtoList = inquiryService.getAllInquirys(userDetails);

        return ResponseJson.success("success", responseDtoList);
    }

    @GetMapping("/{inquiryId}")
    public ResponseJson<InquiryResponse> getInquiry(@PathVariable("inquiryId") Long inquiryId,
                                                    @AuthenticationPrincipal UserDetailsImpl userDetails) {
        InquiryResponse response = inquiryService.getInquiry(inquiryId,userDetails);

        return ResponseJson.success("success", response);
    }


    @PutMapping("/{inquiryId}")
    public ResponseJson<InquiryResponse> updateInquiry(
            @PathVariable("inquiryId") Long inquiryId,
            @RequestBody InquiryRequest request,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        InquiryResponse inquiryResponse = inquiryService.updateInquiry(inquiryId, request, userDetails);

        return ResponseJson.success("success", inquiryResponse);
    }

    @DeleteMapping("/{inquiryId}")
    public ResponseJson<String> deleteInquiry(
            @PathVariable("inquiryId") Long inquiryId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        String title = inquiryService.deleteInquiry(inquiryId, userDetails);

        return ResponseJson.success("success","[" + title + "} 게시글이 삭제 되었습니다.");
    }
}