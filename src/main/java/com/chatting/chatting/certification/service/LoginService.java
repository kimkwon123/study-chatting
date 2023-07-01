package com.chatting.chatting.certification.service;


import com.chatting.chatting.certification.config.PasswordConfig;
import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.model.type.UserRoleEnum;
import com.chatting.chatting.certification.repository.UserRepository;
import com.chatting.chatting.certification.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = repository.findByUserId(userId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("유저를 찾을수 없음");
                });
        return new UserDetailsImpl(user);
    }

}
