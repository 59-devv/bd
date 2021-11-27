package com.sparta.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class KakaoUserInfoDto {
    // 카카오 로그인 관련 Dto
    private Long id;
    private String nickname;
    private String email;
}
