package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.MemoryUserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    MemoryUserRepository memoryUserRepository;


    @Test
    @DisplayName("회원가입")
    void testOfSingUp() {
        UserSignUpRequestDto userSignUpRequestDto = UserSignUpRequestDto.builder()
                .userId("leaf")
                .password("1234")
                .name("김남현")
                .email("leaf.hyeon@kakaocorp.com")
                .build();

        userService.singUp(userSignUpRequestDto);
        BDDMockito.then(memoryUserRepository).should().save(BDDMockito.any());
    }

    @Test
    @DisplayName("아이디로 회원 조회")
    void testOfFindUserByUserId() {
        User user = new User("leaf", "1", "1", "1");
        BDDMockito.given(memoryUserRepository.findUserByUserId("leaf")).willReturn(Optional.of(user));
        User foundUser = userService.findUserByUserId("leaf");
        Assertions.assertThat(foundUser.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 회원 조회시 예외 발생")
    void testOfFindNonExistentUser() {
        BDDMockito.given(memoryUserRepository.findUserByUserId("leaf")).willReturn(Optional.empty());
        Assertions.assertThatThrownBy(() -> userService.findUserByUserId("leaf")).isInstanceOf(UserNotFoundException.class);
    }


}