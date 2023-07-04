package com.chatting.chatting.shop.model.entity;

import com.chatting.chatting.global.model.entity.AuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ShopRoom extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shopPid")
    private Shop shop;

    @Column
    private String sellUserId;

    @Column
    private String buyUserId;

    @Column(unique = true)
    private String roomKey;


}
