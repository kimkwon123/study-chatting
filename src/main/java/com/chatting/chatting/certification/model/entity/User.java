package com.chatting.chatting.certification.model.entity;

import com.chatting.chatting.certification.model.type.UserGender;
import com.chatting.chatting.certification.type.UserRoleEnum;
import com.chatting.chatting.global.model.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Entity
@Builder
@AllArgsConstructor
@Getter
public class User extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userId;
    @Column
    private String userPw;

    @Column(unique = true)
    private String nickname;

    @Enumerated
    @Column
    private UserGender gender;
    @Column(unique = true)
    private String email;

    @Column
    private Long heart;

    @Column
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role = UserRoleEnum.USER;

    protected User() {
    }

}
