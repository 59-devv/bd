package com.sparta.final_project.controller;

import com.sparta.final_project.dto.CommentRequestDto;
import com.sparta.final_project.dto.ContentRequestDto;
import com.sparta.final_project.model.Comment;
import com.sparta.final_project.repository.CommentRepository;
import com.sparta.final_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentRepository commentRepository;
    private final CommentService commentService;

    // 게시글 작성
    @PostMapping("/api/comment/write")
    public Long writeComment(@RequestBody CommentRequestDto requestDto) {
        Comment comment = new Comment(requestDto);
        commentRepository.save(comment);
        return comment.getId();
    }

    // 수정
    @PutMapping("/api/comment/{id}")
    public Long updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.update(id, requestDto);
    }

    @DeleteMapping("/api/comment/{id}")
    public void deleteComment(@PathVariable Long id) {
        commentRepository.deleteById(id);
    }
}
