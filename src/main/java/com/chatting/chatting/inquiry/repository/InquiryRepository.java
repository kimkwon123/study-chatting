package com.chatting.chatting.inquiry.repository;


import com.chatting.chatting.certification.model.entity.User;
import com.chatting.chatting.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
    Optional<List<Inquiry>> findAllByUser(User user);

    Optional<Inquiry> findByUser(User user);
    Optional<Inquiry> findByUserAndInquiryId(User user,Long id);
}
