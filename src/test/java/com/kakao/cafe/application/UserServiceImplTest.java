package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @DisplayName("기존에 존재하지 않는 사용자는 회원 가입이 가능하다")
    @Test
    void checkUserJoin() {
        // given
        User user = new User("2wls", "0224", "윤이진", "483759@naver.com");
        given(userRepository.findByUserId("2wls"))
                .willReturn(Optional.empty());

        // when
        userService.join(user);

        //then
        verify(userRepository).save(any(User.class));
    }

    @DisplayName("이미 존재하는 사용자는 회원 가입을 할 수 없다")
    @Test
    void checkDuplicatedUserJoinException() {
        // given
        User user = new User("2wls", "0224", "윤이진", "483759@naver.com");
        given(userRepository.findByUserId("2wls"))
                .willReturn(Optional.of(user));

        // when
        ThrowableAssert.ThrowingCallable runnable = () -> userService.join(user);

        //then
        assertThatThrownBy(runnable).isInstanceOf(IllegalArgumentException.class);
        verify(userRepository, never()).save(any(User.class));
    }
}