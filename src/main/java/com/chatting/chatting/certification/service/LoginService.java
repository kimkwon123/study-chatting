package com.chatting.chatting.certification.service;


import com.chatting.chatting.certification.config.PasswordConfig;
import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordConfig passwordConfig;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = repository.findByUserId(userId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("유저를 찾을수 없음");
                });

        return new UserDetailsImpl(user);

//        String password = user.getUserPw();
//
//
//        if(user.getUserId().contains("AdminId") ){
//            user.setRole(UserRoleEnum.ADMIN);
//            log.info(user.getRole().toString());
//        }

    }

}
