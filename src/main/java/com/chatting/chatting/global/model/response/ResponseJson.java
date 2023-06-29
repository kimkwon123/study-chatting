package com.chatting.chatting.global.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseJson<T> {

    private String result_code;
    private T result;

    public static <T> ResponseJson<T> error(String errorcode, T errorCode){
        return new ResponseJson<>(errorcode, errorCode);
    }

    public static <T> ResponseJson<T> success(String success, T result){
        return new ResponseJson<>(success,result);
    }

}
