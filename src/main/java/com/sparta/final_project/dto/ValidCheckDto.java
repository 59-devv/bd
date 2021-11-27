package com.sparta.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidCheckDto {

    // 회원가입 시 요구조건 Dto
    private String username;
    private String password;
    private String password2;
    private String email;

    public ValidCheckDto(String username, String password, String password2, String email) {
        this.username = username;
        this.password = password;
        this.password2 = password2;
        this.email = email;
    }
}
