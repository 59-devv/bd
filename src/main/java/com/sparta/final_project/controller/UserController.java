package com.sparta.final_project.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.final_project.security.UserDetailsImpl;
import com.sparta.final_project.service.KakaoUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UserController {

    private final KakaoUserService kakaoUserService;


    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String loginCheck(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginUsername", userDetails.getUsername());
        } else {
            model.addAttribute("loginUsername", null);
        }
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        if (userDetails != null) {
            model.addAttribute("loginUsername", userDetails.getUsername());
        } else {
            model.addAttribute("loginUsername", null);
        }
        return "signup";
    }

    // 카카오 로그인 처리
    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);

        return "redirect:/";
    }
}


