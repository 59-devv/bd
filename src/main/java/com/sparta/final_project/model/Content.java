package com.sparta.final_project.model;

import com.sparta.final_project.dto.ContentRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@RequiredArgsConstructor
@Getter
@Entity
public class Content extends Timestamped {

    // 게시글 테이블 Column 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    // 게시글 기본 생성자
    public Content(String username, String title, String contents) {
        this.username = username;
        this.title = title;
        this.contents = contents;
    }

    // 게시글 작성 시 활용할 Dto
    public Content(ContentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    // 게시글 수정 시 활용할 Dto성
    public void update(ContentRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
