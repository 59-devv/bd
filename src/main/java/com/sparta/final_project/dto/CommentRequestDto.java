package com.sparta.final_project.dto;

import lombok.Getter;

@Getter
public class CommentRequestDto {
    // 댓글 작성 관련 Dto
    private String username;
    private Long contentNo;
    private String comment;
}
