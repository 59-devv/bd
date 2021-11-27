package com.sparta.final_project.service;

import com.sparta.final_project.dto.ValidCheckDto;
import com.sparta.final_project.model.User;
import com.sparta.final_project.model.UserRoleEnum;
import com.sparta.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 회원가입 완료
    public void registerUser(ValidCheckDto requestDto) {
        // 회원 ID 중복 확인
//        System.out.println(requestDto.getUsername());
        String username = requestDto.getUsername();
//        Optional<User> found = userRepository.findByUsername(username);
//        if (found.isPresent()) {
//            throw new IllegalArgumentException("중복된 사용자 ID 가 존재합니다.");
//        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());
        String email = requestDto.getEmail();

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;

        User user = new User(username, password, email, role);
        userRepository.save(user);
    }

    // --> 유효성 체크
    // 중복 아이디 체크
    public Boolean checkDup(ValidCheckDto checkDto) {
        String username = checkDto.getUsername();
        Optional<User> found = userRepository.findByUsername(username);
        return found.isPresent();
    }

    // 아이디 유효성 체크
    public Boolean validUsername(ValidCheckDto checkDto) {
        String username = checkDto.getUsername();
        return Pattern.matches("^[0-9a-zA-Z]{3,}", username);
    }

    // 비밀번호 유효성 체크
    public Boolean validPassword(ValidCheckDto checkDto) {
        String password = checkDto.getPassword();
        if (password.contains(checkDto.getUsername())) {
            return false;
        } else {
            return Pattern.matches("^[0-9a-zA-Z]{4,}", password);
        }
    }

    // 비밀번호 동일 여부 체크
    public Boolean checkPasswordEqual(ValidCheckDto checkDto) {
        return checkDto.getPassword().equals(checkDto.getPassword2());
    }

}
