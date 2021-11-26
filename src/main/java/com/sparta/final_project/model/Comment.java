package com.sparta.final_project.model;

import com.sparta.final_project.dto.CommentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Comment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long contentNo;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String comment;

    public Comment(String username, Long contentNo, String comment) {
        this.username = username;
        this.contentNo = contentNo;
        this.comment = comment;
    }

    public Comment(CommentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contentNo = requestDto.getContentNo();
        this.comment = requestDto.getComment();;
    }

    public void update(CommentRequestDto requestDto) {
        this.comment = requestDto.getComment();
    }
}
