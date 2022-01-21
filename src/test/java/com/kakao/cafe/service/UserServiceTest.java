package com.kakao.cafe.service;

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

    private UserServiceTest() {}

    @ParameterizedTest
    @ValueSource(strings = {"", "abcde"})
    @DisplayName("비밀번호는 6자 이상이어야 한다.")
    void testCreateUserWithShortPassword(String password) {
        // given

        // when
        User user = new User("1", "test", password, "test", "test@test.com");

        // then
        assertThatThrownBy(user::validate)
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("중복된 ID는 가입할 수 없다.")
    void testCreateUserWithDuplicatedUsername() {
        // given
        String usedUsername = "test";

        // when
        User user = new User("1", usedUsername, "123456", "test", "test@test.com");
        Mockito.when(userRepository.isUsernameUsed(usedUsername)).thenReturn(true);

        // then
        assertThatThrownBy(() -> userService.signup(user))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가입되지 않은 회원의 프로필은 조회할 수 없다.")
    void testReadUserWithUnusedUsername() {
        // given
        String unusedUsername = "test";

        // when
        Mockito.when(userRepository.isUsernameUsed(unusedUsername)).thenReturn(false);

        // then
        assertThatThrownBy(() -> userService.getUserByUsername(unusedUsername))
            .isExactlyInstanceOf(ResponseStatusException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcde"})
    @DisplayName("개인정보 변경에서도 비밀번호는 6자 이상이어야 한다.")
    void testUpdateUserWithShortPassword(String password) {
        // given
        String username = "test";

        // when
        User user = new User("1", username, password, "test", "test@test.com");

        // then
        assertThatThrownBy(() -> userService.updateUser(username, user))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가입되지 않은 회원의 정보는 변경할 수 없다.")
    void testUpdateUserWithUnusedUsername() {
        // given
        String unusedUsername = "test";

        // when
        User user = new User("1", unusedUsername, "123456", "test", "test@test.com");
        Mockito.when(userRepository.isUsernameUsed(unusedUsername)).thenReturn(false);

        // then
        assertThatThrownBy(() -> userService.updateUser(unusedUsername, user))
            .isExactlyInstanceOf(ResponseStatusException.class);
    }

//    @Test
//    @DisplayName("UID는 고유한 값은 갖는다.")
//    void testNoDuplicationOnUid() {
//        // given
//
//        // when
//        User user1 = new User("user1", "123456", "test", "test@test.com");
//        User user2 = new User("user2", "123456", "test", "test@test.com");
//
//        // then
//        assertThat(user1.getUid()).isNotEqualTo(user2.getUid());
//    }
}
