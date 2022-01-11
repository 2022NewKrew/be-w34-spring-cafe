package com.kakao.cafe.service;

import com.kakao.cafe.controller.dto.UserSignUpRequestDto;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.exception.UserNotFoundException;
import com.kakao.cafe.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;

    @Test
    @DisplayName("회원가입")
    void testOfSingUp() {
        UserSignUpRequestDto userSignUpRequestDto = UserSignUpRequestDto.builder()
                .userId("leaf")
                .password("1234")
                .name("김남현")
                .email("leaf.hyeon@kakaocorp.com")
                .build();

        userService.signUp(userSignUpRequestDto);
        BDDMockito.then(userRepository).should().save(BDDMockito.any());
    }

    @Test
    @DisplayName("아이디로 회원 조회")
    void testOfFindByUserId() {
        User user = User.builder()
                .userId("leaf")
                .build();
        BDDMockito.given(userRepository.findByUserId("leaf")).willReturn(Optional.of(user));
        User foundUser = userService.findUserByUserId("leaf");
        assertThat(foundUser.getUserId()).isEqualTo(user.getUserId());
    }

    @Test
    @DisplayName("회원 전체 조회")
    void testOfFindAll() {
        User user1 = User.builder()
                .userId("leaf1")
                .build();
        User user2 = User.builder()
                .userId("leaf2")
                .build();
        User user3 = User.builder()
                .userId("leaf3")
                .build();
        BDDMockito.given(userRepository.findAll()).willReturn(List.of(user1, user2, user3));
        List<User> users = userService.findAll();
        assertThat(users).contains(user1, user2, user3);
    }

    @Test
    @DisplayName("존재하지 않는 아이디로 회원 조회시 예외 발생")
    void testOfFindNonExistentUser() {
        BDDMockito.given(userRepository.findByUserId("leaf")).willReturn(Optional.empty());
        assertThatThrownBy(() -> userService.findUserByUserId("leaf")).isInstanceOf(UserNotFoundException.class);
    }


}