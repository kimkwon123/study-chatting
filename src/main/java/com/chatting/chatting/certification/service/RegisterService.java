package com.chatting.chatting.certification.service;

import com.chatting.chatting.certification.model.request.RegisterRequest;
import com.chatting.chatting.certification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;

    public String register(RegisterRequest request){



        return "정상적으로 회원가입이 완료되었습니다.";
    }

    public void sendMail(){

    }

}
