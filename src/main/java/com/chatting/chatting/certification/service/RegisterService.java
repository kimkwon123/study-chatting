package com.chatting.chatting.certification.service;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.model.request.RegisterRequest;
import com.chatting.chatting.certification.model.type.UserGender;
import com.chatting.chatting.certification.repository.UserRepository;
import com.chatting.chatting.certification.type.UserRoleEnum;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.global.model.type.RedisType;
import com.chatting.chatting.global.util.EmailUtil;
import com.chatting.chatting.global.util.RedisUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository userRepository;
    private final EmailUtil emailUtil;
    private final RedisUtil redisUtil;

    private double DEFALUT_TEMPERATURE = 3.6;
    private String WOMAN = "여자";

    public String register(RegisterRequest request){

        //성별 검사 / 유니크 검사
        UserGender userGender = checkRegister(request);

        userRepository.save(User.builder()
                        .userId(request.userId())
                        .userPw(request.userPw())
                        .email(request.email())
                        .gender(userGender)
                        .temperature(DEFALUT_TEMPERATURE)
                        .nickname(request.nickname())
                        .role(UserRoleEnum.USER)
                .build());
        redisUtil.setString(RedisType.EMAIL.name()+request.email(), "", 1, TimeUnit.MILLISECONDS);
        return "정상적으로 회원가입이 완료되었습니다.";
    }

    public String sendMail(String email){
        Optional<User> user = userRepository.findByEmail(email);
        if(user.isEmpty()){
            String random = emailUtil.randomSuccess();
            emailUtil.send_email(email, random);
            redisUtil.setString(RedisType.EMAIL.name()+email, random, 30, TimeUnit.MINUTES);
            return "정상적으로 이메일 발송 되었습니다.";
        }else{
            throw new CustomException(ErrorCode.SAME_EMAIL);
        }
    }

    private UserGender checkRegister(RegisterRequest request){

        Optional<User> user = userRepository.findByUserIdOrNickname(request.userId(), request.nickname());

        if(!(user.isEmpty())){
            throw new CustomException(ErrorCode.SAME_REGISTER);
        }

        String emailSuccessKey = redisUtil.getString(RedisType.EMAIL.name()+request.email());
        if(Objects.isNull(emailSuccessKey)){
            throw new CustomException(ErrorCode.NO_SUCCESS);
        }else{
            if(emailSuccessKey.equals(request.successKey())){
                UserGender userGender = UserGender.MAN;
                if(request.gender().equals(WOMAN)){
                    userGender = UserGender.GIRL;
                }
                return userGender;
            }else{
                throw new CustomException(ErrorCode.NO_SUCCESSKEY);
            }
        }
    }

}
