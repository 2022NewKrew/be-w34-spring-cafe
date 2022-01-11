package com.kakao.cafe.application.user;

import com.kakao.cafe.application.dto.UserAccountEnrollCommand;
import com.kakao.cafe.domain.user.UserAccount;
import com.kakao.cafe.domain.user.UserAccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserAccountServiceTest {

    @Mock
    UserAccountRepository userAccountRepository;

    @Test
    @DisplayName("중복 테스트")
    void enrollDuplicateTest() {
        //given
        String password = "1234";
        String username = "peach";
        String email = "peach@kakao.com";
        given(userAccountRepository.findByEmail(email)).willReturn(Optional.of(UserAccount.builder().build()));

        UserAccountService userAccountService = new UserAccountService(userAccountRepository);

        //when then
        Assertions.assertThatThrownBy(
                () -> userAccountService.enroll(new UserAccountEnrollCommand(password, username, email)))
                .isInstanceOf(IllegalArgumentException.class);
    }
}