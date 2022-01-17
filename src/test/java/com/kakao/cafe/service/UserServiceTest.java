package com.kakao.cafe.service;

import com.kakao.cafe.domain.user.JdbcUserRepository;
import com.kakao.cafe.domain.user.MemoryUserRepository;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    UserService userService;
    MemoryUserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository = new MemoryUserRepository();
        userService = new UserService(userRepository);
    }

    @AfterEach
    void tearDown(){
        userRepository.clear();
    }

    @Test
    @DisplayName("회원 가입 성공")
    void join() {
        CreateUserDto createUser = userBuilder("test");

        userService.join(createUser);

        ShowUserDto findUser = userService.findProfile(createUser.getUserId());
        assertThat(createUser.getUserId()).isEqualTo(findUser.getUserId());
    }

    @Test
    @DisplayName("아이디 중복 시 회원가입 실패")
    void duplicateUserId(){
        CreateUserDto createUser1 = userBuilder("test");
        CreateUserDto createUser2 = userBuilder("test");

        userService.join(createUser1);
        assertThrows(IllegalArgumentException.class, () -> userService.join(createUser2));
    }

    @Test
    @DisplayName("전체 사용자 조회")
    void findAllUser() {
        int size = 5;
        List<CreateUserDto> userList = IntStream.range(1, size).boxed()
                .map(num -> userBuilder("test" + num))
                .collect(Collectors.toList());

        userList.forEach(user -> userService.join(user));

        List<ShowUserDto> allUser = userService.findAllUser();

        assertThat(userList.size()).isEqualTo(allUser.size());
    }

    @Test
    @DisplayName("사용자 수정 성공")
    void editProfile() {
        CreateUserDto userDto = userBuilder("test");
        userService.join(userDto);
        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .password(userDto.getPassword())
                .newPassword("11111")
                .name("test2")
                .email("test2@test.com")
                .build();

        ShowUserDto user = userService.editProfile(userDto.getUserId(), updateUserDto);
        assertThat(user.getName()).isEqualTo(updateUserDto.getName());
        assertThat(user.getEmail()).isEqualTo(updateUserDto.getEmail());
    }

    @Test
    @DisplayName("현재 비밀번호가 맞지 않으면 수정 실패")
    void failEditByWrongPassword(){
        CreateUserDto userDto = userBuilder("test");
        userService.join(userDto);
        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .password("wrongPassword")
                .name("test2")
                .email("test2@test.com")
                .build();

        assertThrows(IllegalArgumentException.class, () -> userService.editProfile(userDto.getUserId(), updateUserDto));
    }

    private CreateUserDto userBuilder(String userId){
        return CreateUserDto.builder()
                .userId(userId)
                .password("1234")
                .name(userId)
                .email("test@test.com")
                .build();
    }

}
