package com.sparta.final_project.controller;

import com.sparta.final_project.dto.ValidCheckDto;
import com.sparta.final_project.service.UserService;
import com.sparta.final_project.service.UserValidService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class UserCheckController {

    private final UserValidService userValidService;
    private final UserService userService;

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    @ResponseBody
    public Map<String, String> registerUser(@RequestBody ValidCheckDto requestDto, Model model) {
        String idCheck = "";
        if (userService.checkDup(requestDto)) {
            idCheck = "중복된 아이디입니다.";
        } else {
            if (!userService.validUsername(requestDto)) {
                idCheck = "3자 이상의 영문 및 숫자로 이루어져야 합니다.";
            }
        }

        String pwCheck = "";
        if (userService.validPassword(requestDto)) {
        } else {
            pwCheck = "4자 이상의 영문 및 숫자로 이루어져야 하며, 닉네임을 포함할 수 없습니다.";
        }

        String pwCheck2 = "";
        if (userService.checkPasswordEqual(requestDto)) {
        } else {
            pwCheck2 = "비밀번호가 일치하지 않습니다. 다시 확인해주세요.";
        }

        Map<String, String> result = new HashMap<>();
        result.put("idCheck", idCheck);
        result.put("pwCheck", pwCheck);
        result.put("pwCheck2", pwCheck2);


        if (idCheck.equals("") && pwCheck.equals("") && pwCheck2.equals("")) {
            result.put("result", "Success");
            userService.registerUser(requestDto);
        } else {
            result.put("result", "Fail");
        }
        System.out.println(idCheck + pwCheck + pwCheck2);
        return result;
    }



//    // 아이디 중복 체크
//    @ResponseBody
//    @PostMapping("/api/sign_up/check_dup")
//    public Boolean checkDup(@RequestBody SignupRequestDto checkDto) {
//        System.out.println(checkDto);
//        return userValidService.checkDup(checkDto);
//    }
}
