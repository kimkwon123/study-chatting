package com.chatting.chatting.shop.model.entity;

import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.global.model.entity.AuditingEntity;
import com.chatting.chatting.shop.model.type.CateType;
import com.chatting.chatting.shop.model.type.ProgressiveType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Shop extends AuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userPid")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop", fetch = FetchType.LAZY)
    private List<ShopHeart> shopHearts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shop", fetch = FetchType.LAZY)
    private List<ShopRoom> shopRooms = new ArrayList<>();

    @Setter
    @Column
    private String shopName;
    @Setter
    @Column
    private String shopContent;

    @Setter
    @Column
    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> img;

    @Setter
    @Column
    private Integer money;

    @Setter
    @Column
    private Boolean auction;

    @Setter
    @Enumerated
    @Column
    private ProgressiveType progressive;
    @Setter
    @Enumerated
    @Column
    private CateType cate;

}
