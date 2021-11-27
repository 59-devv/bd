package com.sparta.final_project.model;

import com.sparta.final_project.dto.CommentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    // Comment 테이블 Column 설정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contentNo;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    // 코멘트 기본 생성자 생성
    public Comment(String username, Long contentNo, String comment) {
        this.username = username;
        this.contentNo = contentNo;
        this.comment = comment;
    }

    // 코멘트 작성 시 활용할 Dto
    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contentNo = requestDto.getContentNo();
        this.comment = requestDto.getComment();;
    }

    // 코멘트 수정 시 활용할 Dto
    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
