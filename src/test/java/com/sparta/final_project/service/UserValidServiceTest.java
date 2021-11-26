package com.sparta.final_project.service;

import com.sparta.final_project.dto.ValidCheckDto;
import com.sparta.final_project.model.User;
import com.sparta.final_project.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.sparta.final_project.model.UserRoleEnum.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserValidServiceTest {

    @Mock
    private UserRepository userRepository;

    @Test
    @DisplayName("아이디 중복 테스트1 - 중복있음")
    void idDuplicationCheck1() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);
        User user = new User("mike", "hello", "mike@hi.com,", USER);
        when(userRepository.save(user)).thenReturn(user);


        //유저 생성
        // 클라이언트로부터 받는 Dto
        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "aasdfqwer", "aasdfqwer", "lucifer@hello.com"
        );

        //when
        User user1 = userRepository.save(user);

        //then
        assertEquals(user1.getUsername(), requestDto.getUsername());
    }

    @Test
    @DisplayName("아이디 중복 테스트2 - 중복있음")
    void idDuplicationCheck2() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);
        User user = new User("lucifer", "hello", "mike@hi.com,", USER);
        when(userRepository.save(user)).thenReturn(user);


        //유저 생성
        // 클라이언트로부터 받는 Dto
        ValidCheckDto requestDto = new ValidCheckDto(
                "lucifer", "aasdfqwer", "aasdfqwer", "lucifer@hello.com"
        );

        //when
        User user1 = userRepository.save(user);

        //then
        assertEquals(user1.getUsername(), requestDto.getUsername());
    }

    @Test
    @DisplayName("아이디 유효성 체크1 - 3자 미만 오류")
    void idValidCheck() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mi", "aasdfqwer", "aasdfqwer", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.validUsername(requestDto);

        //then
        assertEquals(validCheckResult, false);
    }

    @Test
    @DisplayName("아이디 유효성 체크2 - 통과")
    void idValidCheck2() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "himike", "himike", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.validUsername(requestDto);

        //then
        assertEquals(validCheckResult, true);
    }


    @Test
    @DisplayName("비밀번호 유효성 체크1 - 아이디 포함 실패")
    void pwValidCheck1() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "himike", "himike", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.validPassword(requestDto);

        //then
        assertEquals(validCheckResult, false);
    }

    @Test
    @DisplayName("비밀번호 유효성 체크2 - 성공")
    void pwValidCheck2() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "asdfqwer", "asdfqwer", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.validPassword(requestDto);

        //then
        assertEquals(validCheckResult, true);
    }

    @Test
    @DisplayName("비밀번호 동일 체크1 - 성공")
    void pwEqualCheck1() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "asdfqwer", "asdfqwer", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.checkPasswordEqual(requestDto);

        //then
        assertEquals(validCheckResult, true);
    }

    @Test
    @DisplayName("비밀번호 동일 체크2 - 실패")
    void pwEqualCheck2() {
        //given
        UserValidService userValidService = new UserValidService(userRepository);

        ValidCheckDto requestDto = new ValidCheckDto(
                "mike", "asdfqwer", "aqwed", "lucifer@hello.com"
        );

        //when
        Boolean validCheckResult = userValidService.checkPasswordEqual(requestDto);

        //then
        assertEquals(validCheckResult, false);
    }
}