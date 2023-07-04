package com.chatting.chatting.certification.service;


import com.chatting.chatting.certification.config.PasswordConfig;
import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.certification.model.type.UserRoleEnum;
import com.chatting.chatting.certification.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LoginService implements UserDetailsService {

    private final UserRepository repository;
    private final PasswordConfig passwordConfig;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = repository.findByUserId(userId)
                .orElseThrow(() -> {
                    throw new UsernameNotFoundException("유저를 찾을수 없음");
                });

//        String password = user.getUserPw();
//
//        if(user.getUserId().contains("AdminId") ){
//            user.setRole(UserRoleEnum.ADMIN);
//            user.setStarFromCustomer((double)0);
//            log.info(user.getRole().toString());
//        }

        return new UserDetailsImpl(user);

    }

}
