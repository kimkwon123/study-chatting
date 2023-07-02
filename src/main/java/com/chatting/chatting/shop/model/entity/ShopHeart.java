package com.chatting.chatting.shop.model.entity;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.global.model.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
public class ShopHeart extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopPid")
    private Shop shop;

    @Column
    private String userId;


}
