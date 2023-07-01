package com.chatting.chatting.reply.service;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.inquiry.repository.InquiryRepository;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.reply.entity.Reply;
import com.chatting.chatting.reply.repository.ReplyRepository;
import com.chatting.chatting.reply.request.ReplyRequest;
import com.chatting.chatting.reply.response.ReplyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReplyService {
    private final ReplyRepository replyRepository;
    private final InquiryRepository inquiryRepository;

    @Transactional
    public String createReply(Long inquiryId, ReplyRequest request, UserDetailsImpl userDetails) {

        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_INQUIRY)
        );

        if(Objects.isNull(request.content())){
            throw new CustomException(ErrorCode.NO_REPLY_CONTENT);
        }
        if(!checkAdmin((userDetails))){
            throw new CustomException(ErrorCode.NO_ADMIN);
        }
        replyRepository.save(new Reply(request,inquiry,userDetails));

        return "답변 완료";
    }


    @Transactional
    public ReplyResponse updateReply(Long replyId, InquiryRequest request, UserDetailsImpl userDetails) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_REPLY)
        );
        if(checkEqualAdmin(userDetails,reply)){
            reply.update(request);
        }
        else{
            throw new CustomException(ErrorCode.NO_AUTHORITY_TO_REPLY);
        }

        return new ReplyResponse(reply);
    }

    @Transactional
    public String deleteReply(Long replyId, UserDetailsImpl userDetails) {
        Reply reply = replyRepository.findById(replyId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_REPLY)
        );
        if(checkEqualAdmin(userDetails,reply)){
            replyRepository.delete(reply);
        }
        else{
            throw new CustomException(ErrorCode.NO_AUTHORITY_TO_REPLY);
        }

        return userDetails.getUsername() + "" ;
    }



    public boolean checkAdmin(UserDetailsImpl userDetails){
       System.out.println("Role === " + userDetails.getUser().getRole().toString());
        if(!userDetails.getUser().getRole().toString().equals("ADMIN")){
            return false;
        }
        return true;
    }

    public boolean checkEqualAdmin(UserDetailsImpl userDetails, Reply reply){
        if(userDetails.getUsername().equals(reply.getUser().getUserId())){
            return true;
        }
        return false;
    }


}
