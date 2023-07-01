package com.chatting.chatting.inquiry.service;

import com.chatting.chatting.certification.model.UserDetailsImpl;
import com.chatting.chatting.global.exection.CustomException;
import com.chatting.chatting.global.model.type.ErrorCode;
import com.chatting.chatting.inquiry.entity.Inquiry;
import com.chatting.chatting.inquiry.repository.InquiryRepository;
import com.chatting.chatting.inquiry.request.InquiryRequest;
import com.chatting.chatting.inquiry.response.InquiryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryService {
    private final InquiryRepository inquiryRepository;

    @Transactional
    public String createInquiry(InquiryRequest request, UserDetailsImpl userDetails) {
        
        if(Objects.isNull(request.title())){
            throw new CustomException(ErrorCode.NO_INQUIRY_TITLE);
        }else if(Objects.isNull(request.content())){
            throw new CustomException(ErrorCode.NO_INQUIRY_CONTENT);
        }else if(Objects.isNull(request.category())){
            throw new CustomException(ErrorCode.NO_INQUIRY_CATEGORY);
        }

        inquiryRepository.save(new Inquiry(request,userDetails));

        return "문의가 등록되었습니다";
        
    }


    public List<InquiryResponse> getAllInquirys(UserDetailsImpl userDetails) {
        Optional<List<Inquiry>> inquiryList = inquiryRepository.findAllByUser(userDetails.getUser());

        if(checkUser(userDetails,inquiryList.get().get(0))){
            return inquiryList.get().stream().map(factor -> new InquiryResponse(factor)).collect(Collectors.toList());
        }

        return null;
    }

    public InquiryResponse getInquiry(Long inquiryId, UserDetailsImpl userDetails) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_INQUIRY)
        );

        if(checkUser(userDetails,inquiry)){
            return new InquiryResponse(inquiry);
        }

        return null;
    }

    @Transactional
    public InquiryResponse updateInquiry(Long inquiryId, InquiryRequest request, UserDetailsImpl userDetails) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_INQUIRY)
        );

        if(checkUser(userDetails,inquiry)){
            inquiry.update(request);
        }

        return new InquiryResponse(inquiry);
    }

    @Transactional
    public String deleteInquiry(Long inquiryId, UserDetailsImpl userDetails) {
        Inquiry inquiry = inquiryRepository.findById(inquiryId).orElseThrow(() ->
                new CustomException(ErrorCode.NO_INQUIRY)
        );

        if(checkUser(userDetails,inquiry)){
            inquiryRepository.delete(inquiry);
        }

        return inquiry.getTitle();
    }

    public boolean checkUser(UserDetailsImpl userDetails, Inquiry inquiry){
        if(userDetails.getUsername().equals(inquiry.getUser().getUserId())){
            return true;
        }
        throw new CustomException(ErrorCode.NO_AUTHORITY_TO_INQUIRY);
    }

}
