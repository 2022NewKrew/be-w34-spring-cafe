package com.kakao.cafe.service;

import com.kakao.cafe.dto.user.CreateUserDto;
import com.kakao.cafe.dto.user.LoginDto;
import com.kakao.cafe.dto.user.ShowUserDto;
import com.kakao.cafe.dto.user.UpdateUserDto;
import com.kakao.cafe.util.exception.NotFoundException;
import com.kakao.cafe.util.exception.UnAuthorizedException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Transactional
@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

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
    void duplicateUserId() {
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

        List<String> findAllUserId = userService.findAllUser().stream()
                .map(ShowUserDto::getUserId)
                .collect(Collectors.toList());

        userList.forEach(user -> assertThat(findAllUserId).contains(user.getUserId()));
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

        ShowUserDto user = userService.updateProfile(userDto.getUserId(), updateUserDto);
        assertThat(user.getName()).isEqualTo(updateUserDto.getName());
        assertThat(user.getEmail()).isEqualTo(updateUserDto.getEmail());
    }

    @Test
    @DisplayName("현재 비밀번호가 맞지 않으면 수정 실패")
    void failEditByWrongPassword() {
        CreateUserDto userDto = userBuilder("test");
        userService.join(userDto);
        UpdateUserDto updateUserDto = UpdateUserDto.builder()
                .password("wrongPassword")
                .name("test2")
                .email("test2@test.com")
                .build();

        assertThrows(UnAuthorizedException.class, () -> userService.updateProfile(userDto.getUserId(), updateUserDto));
    }

    @Test
    @DisplayName("로그인 성공")
    void successLogin() {
        CreateUserDto user = userBuilder("test");
        userService.join(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(user.getUserId());
        loginDto.setPassword(user.getPassword());
        ShowUserDto loginUser = userService.login(loginDto);

        assertThat(loginUser.getName()).isEqualTo(user.getName());
        assertThat(loginUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    @DisplayName("로그인 실패 - 아이디 오류")
    void notFoundByUserId() {
        CreateUserDto user = userBuilder("test");
        userService.join(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId("test2");
        loginDto.setPassword(user.getPassword());
        assertThrows(NotFoundException.class, () -> userService.login(loginDto));
    }

    @Test
    @DisplayName("로그인 실패 - 비밀번호 오류")
    void incorrectPassword() {
        CreateUserDto user = userBuilder("test");
        userService.join(user);

        LoginDto loginDto = new LoginDto();
        loginDto.setUserId(user.getUserId());
        loginDto.setPassword("wrongPassword");
        assertThrows(UnAuthorizedException.class, () -> userService.login(loginDto));
    }

    private CreateUserDto userBuilder(String userId) {
        return CreateUserDto.builder()
                .userId(userId)
                .password("1234")
                .name(userId)
                .email("test@test.com")
                .build();
    }

}
