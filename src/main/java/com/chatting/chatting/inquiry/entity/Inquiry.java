package com.chatting.chatting.inquiry.entity;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.inquiry.type.InquiryCategory;
import com.chatting.chatting.reply.entity.Reply;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@NoArgsConstructor
@Getter
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;

    @Column
    private String title;
    @Column
    private String content;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private InquiryCategory category;

    @Column
    private String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "inquiry",cascade = CascadeType.ALL)
    private List<Reply> reply;

    public Inquiry(InquiryRequest request, UserDetailsImpl userDetails) {
        this.title =request.title();
        this.content = request.content();
        this.category = request.category();
        this.imageUrl = request.ImageUrl();
        this.user = userDetails.getUser();
    }

    public void update(InquiryRequest request) {
        this.title = request.title();
        this.content = request.content();
        this.category = request.category();
        this.imageUrl = request.ImageUrl();

    }
}