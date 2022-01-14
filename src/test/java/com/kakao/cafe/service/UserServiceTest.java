package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @ParameterizedTest
    @ValueSource(strings = {"", "abcde"})
    @DisplayName("비밀번호는 6자 이상이어야 한다.")
    void testCreateUserWithShortPassword(String password) {
        // given

        // when
        User user = new User("rootkwak528", password, "root.directory", "root.directory@email.com");

        // then
        assertThatThrownBy(user::validate)
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 ID는 가입할 수 없다.")
    void testCreateUserWithDuplicatedUserId() {
        // given
        String usedUserId = "rootkwak528";

        // when
        User user = new User(usedUserId, "123456", "root.directory", "root.directory@email.com");
        Mockito.when(userRepository.isUserIdUsed(usedUserId)).thenReturn(true);

        // then
        assertThatThrownBy(() -> userService.signup(user))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가입되지 않은 회원의 프로필은 조회할 수 없다.")
    void testReadUserWithUnusedUserId() {
        // given
        String unusedUserId = "rootkwak528";

        // when
        Mockito.when(userRepository.isUserIdUsed(unusedUserId)).thenReturn(false);

        // then
        assertThatThrownBy(() -> userService.getUserFromUserId(unusedUserId))
            .isExactlyInstanceOf(ResponseStatusException.class);
    }
}
