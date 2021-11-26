package com.sparta.final_project.service;

import com.sparta.final_project.dto.ValidCheckDto;
import com.sparta.final_project.model.User;
import com.sparta.final_project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;


@Service
@RequiredArgsConstructor
public class UserValidService {

    private final UserRepository userRepository;

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
