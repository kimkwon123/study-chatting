package com.chatting.chatting.reply.service;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.reply.entity.Reply;
import com.chatting.chatting.reply.repository.ReplyRepository;
import com.chatting.chatting.reply.request.RatingByUserRequest;
import com.chatting.chatting.reply.response.RatingByUserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class RatingByUserService {

    private final ReplyRepository replyRepository;
    @Transactional
    public RatingByUserResponse RatingToAgent(Long replyId, RatingByUserRequest request , UserDetailsImpl userDetails) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_REPLY)
        );

        Hibernate.initialize(reply.getUser());

        log.info(reply.getUser().getUserId());

        if(!checkThisUserInquiryWrite(userDetails,reply)){
            throw new CustomException(ErrorCode.NO_WRITER_TO_INQUIRY);
        }

        Double rating_star_avg = reply.getUser().updateRating(request.rating_star());

        return new RatingByUserResponse(userDetails.getUsername(), rating_star_avg);
    }



    public boolean checkThisUserInquiryWrite(UserDetailsImpl userDetails, Reply reply){
        log.info(userDetails.getUsername());

        if(!reply.getInquiry().getUser().getUserId().equals(userDetails.getUsername())){
            return false;
        }
        return true;

    }
}
