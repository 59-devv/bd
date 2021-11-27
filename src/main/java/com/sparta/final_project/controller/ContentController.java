package com.sparta.final_project.controller;

import com.sparta.final_project.model.Content;
import com.sparta.final_project.repository.ContentRepository;
import com.sparta.final_project.dto.ContentRequestDto;
import com.sparta.final_project.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentController {

    private final ContentRepository contentRepository;
    private final ContentService contentService;

    // 전체 게시글 조회
    @GetMapping("/api/contents")
    public List<Content> readContents() {
        return contentRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 쓰기
    @PostMapping("/api/write")
    public Content createContent(@RequestBody ContentRequestDto requestDto) {
        Content content = new Content(requestDto);
        contentRepository.save(content);
        return content;
    }

    // 게시글 수정
    @PutMapping("/api/content/{id}")
    public Long updateContent(@PathVariable Long id, @RequestBody ContentRequestDto requestDto) {
        return contentService.update(id, requestDto);
    }

    // 게시글 삭제
    @DeleteMapping("/api/content/{id}")
    public void deleteContent(@PathVariable Long id) {
        contentRepository.deleteById(id);
    }
}
