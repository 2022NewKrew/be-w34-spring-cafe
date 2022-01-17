package com.kakao.cafe.application.user;

import com.kakao.cafe.user.application.EnrollUserAccountService;
import com.kakao.cafe.user.application.dto.command.UserAccountEnrollCommand;
import com.kakao.cafe.user.application.port.out.LoadUserAccountPort;
import com.kakao.cafe.user.domain.UserAccount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EnrollUserAccountServiceTest {

    @Mock
    LoadUserAccountPort loadUserAccountPort;

    @InjectMocks
    EnrollUserAccountService enrollUserAccountService;

    @Test
    @DisplayName("중복 테스트")
    void enrollDuplicateTest() {
        //given
        String password = "1234";
        String username = "peach";
        String email = "peach@kakao.com";
        given(loadUserAccountPort.findByEmail(email)).willReturn(Optional.of(UserAccount.builder().build()));

        //when then
        Assertions.assertThatThrownBy(
                () -> enrollUserAccountService.enroll(new UserAccountEnrollCommand(password, username, email)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}