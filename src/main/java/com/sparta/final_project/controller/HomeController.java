package com.sparta.final_project.controller;

import com.sparta.final_project.model.Comment;
import com.sparta.final_project.model.Content;
import com.sparta.final_project.repository.CommentRepository;
import com.sparta.final_project.repository.ContentRepository;
import com.sparta.final_project.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final ContentRepository contentRepository;
    private final CommentRepository commentRepository;

    // 홈페이지
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginUsername", userDetails.getUsername());
        } else {
            model.addAttribute("loginUsername", null);
        }
        return "index";
    }

    // 게시글 상세페이지
    @GetMapping("/content/{id}")
    public String home(Model model, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        // 컨텐츠 넘기기
        Optional<Content> content = contentRepository.findById(id);
        if (content.isPresent()) {
            model.addAttribute("result", content.get());
        }

        // 코멘트 넘기기
        List<Comment> comment = commentRepository.findAllByContentNoOrderByCreatedAtDesc(id);
        if (comment.size() != 0) {
            model.addAttribute("commentResult", comment);
        } else {
            model.addAttribute("commentResult", "NoValue");
        }

        // 로그인 아이디 넘기기
        if (userDetails != null) {
            model.addAttribute("loginUsername", userDetails.getUsername());
        } else {
            model.addAttribute("loginUsername", null);
        }
        return "detail";
    }
}