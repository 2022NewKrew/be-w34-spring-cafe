package com.kakao.cafe.application;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserPort;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserPort userPort;

    @DisplayName("유저 ID로 사용자를 조회할 수 있다")
    @Test
    void checkFindUserByUserId() {
        // given
        User expectedUser = new User("2wls", "0224", "윤이진", "483759@naver.com");
        Optional<User> expectedOptionalUser = Optional.of(expectedUser);
        String userId = "2wls";
        given(userPort.findByUserId(userId))
                .willReturn(expectedOptionalUser);

        // when
        User user = userService.findByUserId(userId);

        //then
        verify(userPort).findByUserId(userId);
        assertThat(user)
                .usingRecursiveComparison()
                .isEqualTo(expectedUser);
    }

    @DisplayName("존재하지 않는 유저 ID로 사용자 조회를 할 수 없다")
    @Test
    void checkFindNonExistUserByUserIdException() {
        // given
        String userIdThatDoesNotExist = "2wls";
        given(userPort.findByUserId(userIdThatDoesNotExist))
                .willReturn(Optional.empty());

        // when
        ThrowableAssert.ThrowingCallable runnable = () -> userService.findByUserId(userIdThatDoesNotExist);

        //then
        assertThatThrownBy(runnable).isInstanceOf(IllegalArgumentException.class);
        verify(userPort).findByUserId(userIdThatDoesNotExist);
    }

    @DisplayName("모든 사용자의 목록을 조회할 수 있다")
    @Test
    void checkFindAllUserList() {
        // given
        List<User> users = List.of(
                new User("2wls", "0224", "윤이진", "483759@naver.com"),
                new User("1234", "1234", "1234", "1234@naver.com")
        );
        given(userPort.findAll())
                .willReturn(users);

        // when
        List<User> userList = userService.findAllUser();

        //then
        verify(userPort).findAll();
        assertThat(userList)
                .extracting("userId", "password", "name", "email")
                .containsExactly(
                        tuple("2wls", "0224", "윤이진", "483759@naver.com"),
                        tuple("1234", "1234", "1234", "1234@naver.com")
                );
    }

    @DisplayName("기존에 존재하지 않는 사용자는 회원 가입이 가능하다")
    @Test
    void checkUserJoin() {
        // given
        User user = new User("2wls", "0224", "윤이진", "483759@naver.com");
        given(userPort.findByUserId("2wls"))
                .willReturn(Optional.empty());

        // when
        userService.join(user);

        //then
        verify(userPort).save(any(User.class));
    }

    @DisplayName("이미 존재하는 사용자는 회원 가입을 할 수 없다")
    @Test
    void checkDuplicatedUserJoinException() {
        // given
        User user = new User("2wls", "0224", "윤이진", "483759@naver.com");
        given(userPort.findByUserId("2wls"))
                .willReturn(Optional.of(user));

        // when
        ThrowableAssert.ThrowingCallable runnable = () -> userService.join(user);

        //then
        verify(userPort, never()).save(any(User.class));
        assertThatThrownBy(runnable).isInstanceOf(IllegalArgumentException.class);
    }
}