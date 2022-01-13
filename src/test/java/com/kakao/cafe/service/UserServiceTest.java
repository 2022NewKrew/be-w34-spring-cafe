package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.repository.MemoryUserRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequestDto;
import com.kakao.cafe.web.dto.UserProfileResponseDto;
import com.kakao.cafe.web.dto.UserResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @DisplayName("회원가입이 잘 되는가")
    @Test
    void signUp() {

        //given
        String userId = "testId";
        String password = "testPassword";
        String name = "tom";
        String email = "tom.test@kakaocorp.com";
        User user = new User(1L, userId, password, name, email);

        //when
        UserCreateRequestDto requestDto = new UserCreateRequestDto(userId, password, name, email);
        userService.signUp(requestDto);

        //then
        assertThat(userRepository.findByUserId("testId")).isEqualTo(user);
    }

    @DisplayName("회원목록이 잘 불러와지는가")
    @Test
    void getUserList() {

        //given
        List<String> userIdList = new ArrayList<>(Arrays.asList("testId1", "testId2", "testId3", "testId4", "testId5"));
        List<String> passwordList = new ArrayList<>(Arrays.asList("testPw1", "testPw2", "testPw3", "testPw4", "testPw5"));
        List<String> nameList = new ArrayList<>(Arrays.asList("tom", "cat", "was", "tdd", "jpa"));
        List<String> emailList = new ArrayList<>(Arrays.asList("tom.test@kakaocorp.com", "cat.test@kakaocorp.com", "was.test@kakaocorp.com", "tdd.test@kakaocorp.com", "jpa.test@kakaocorp.com"));

        for (int i = 0; i < 4; i++) {
            userService.signUp(new UserCreateRequestDto(userIdList.get(i), passwordList.get(i), nameList.get(i), emailList.get(i)));
        }

        //when
        List<UserResponseDto> userList = userService.getUserList();

        //then
        assertThat(userList.size()).isEqualTo(4);
    }

    @DisplayName("회원 프로필 정보가 잘 불러와지는가")
    @Test
    void getUserProfile() {

        //given
        String userId = "testId";
        String password = "testPassword";
        String name = "tom";
        String email = "tom.test@kakaocorp.com";
        User user = new User(1L, userId, password, name, email);
        UserProfileResponseDto expectedDto = UserProfileResponseDto.from(user);

        UserCreateRequestDto requestDto = new UserCreateRequestDto(userId, password, name, email);
        userService.signUp(requestDto);

        //when
        UserProfileResponseDto responseDto = userService.getUserProfile("testId");

        //then
        assertThat(responseDto).isEqualTo(expectedDto);
    }
}