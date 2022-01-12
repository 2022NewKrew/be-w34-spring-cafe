package com.kakao.cafe.application.user;

import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.verify;
import static org.mockito.Mockito.never;

@ExtendWith(MockitoExtension.class)
class SignUpFindUserServiceTest {

    @InjectMocks
    SignUpUserService signUpUserService;

    @Mock
    FindUserPort findUserPort;

    @Mock
    SignUpUserPort signUpUserPort;

    @DisplayName("기존에 존재하지 않는 사용자는 회원 가입이 가능하다")
    @Test
    void checkUserJoin() {
        // given
        UserVo user = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        given(findUserPort.findByUserId("2wls"))
                .willReturn(Optional.empty());

        // when
        signUpUserService.join(user);

        //then
        verify(signUpUserPort).save(any(User.class));
    }

    @DisplayName("이미 존재하는 사용자는 회원 가입을 할 수 없다")
    @Test
    void checkDuplicatedUserJoinException() {
        // given
        UserVo userVo = new UserVo("2wls", "0224", "윤이진", "483759@naver.com");
        User user = new User("2wls", "0224", "윤이진", "483759@naver.com");
        given(findUserPort.findByUserId("2wls"))
                .willReturn(Optional.of(user));

        // when
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> signUpUserService.join(userVo));

        //then
        assertThat(exception.getMessage())
                .isEqualTo("이미 존재하는 ID는 가입할 수 없습니다.");
        verify(signUpUserPort, never()).save(any(User.class));
    }
}