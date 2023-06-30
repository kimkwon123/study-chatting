package com.chatting.chatting.certification.controller;

import com.chatting.chatting.certification.model.request.RegisterRequest;
import com.chatting.chatting.certification.service.RegisterService;
import com.chatting.chatting.global.model.response.ResponseJson;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Objects;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService service;

    @PostMapping("/send-mail")
    public ResponseJson<String> sendMail(
            @RequestBody HashMap<String, Object> ar
    ) {
        String msg = service.sendMail(ar.get("email").toString());
        return ResponseJson.success("success", msg);
    }
    @PostMapping
    public ResponseJson<String> register(
            @RequestBody RegisterRequest req
            ) {
        String msg = service.register(req);
        return ResponseJson.success("success", msg);
    }

}
