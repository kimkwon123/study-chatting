package com.chatting.chatting.certification.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record RegisterRequest(

        @NotNull
        @NotBlank
        @NotEmpty
        String userId,
        @NotNull
        @NotBlank
        @NotEmpty
        String userPw,
        @NotNull
        @NotBlank
        @NotEmpty
        String nickname,
        @NotNull
        @NotBlank
        @NotEmpty
        String gender,
        @NotNull
        @NotBlank
        @NotEmpty
        String email,
        @NotNull
        @NotBlank
        @NotEmpty
        String successKey
) {
}
