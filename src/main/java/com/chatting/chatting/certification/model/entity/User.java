package com.chatting.chatting.certification.model.entity;

import com.chatting.chatting.certification.model.type.UserGender;
import com.chatting.chatting.certification.model.type.UserRoleEnum;
import com.chatting.chatting.global.model.entity.AuditingEntity;
import com.chatting.chatting.shop.model.entity.Shop;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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
    private Double temperature;

    @Setter
    @Column
    Double starFromCustomer = (double) 0;

    @Column
    @Enumerated(EnumType.STRING)
    @Setter
    private UserRoleEnum role;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Shop> shopList = new ArrayList<>();

    int cnt_reply=0;

    protected User() {
    }

    public Double updateRating(int rating) {
        double sum = this.starFromCustomer * this.cnt_reply;
        this.cnt_reply++;
        this.starFromCustomer = (sum+rating) / (this.cnt_reply) ;

        return this.starFromCustomer;
    }
}
