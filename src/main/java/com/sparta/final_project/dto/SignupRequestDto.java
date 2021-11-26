package com.sparta.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    private String username;
    private String password;
    private String password2;
    private String email;

    // 아이디 중복 확인용
    public SignupRequestDto(String username) {
        this.username = username;
    }

    // 기본
    public SignupRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}