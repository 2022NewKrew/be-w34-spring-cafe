package com.kakao.cafe.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.kakao.cafe.domain.User;
import com.kakao.cafe.domain.UserDto;
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
    void testCreateUserWithDuplicatedUserId() {
        // given
        String usedUserId = "test";

        // when
        UserDto userDto = new UserDto(usedUserId, "123456", "test", "test@test.com");
        Mockito.when(userRepository.isUserIdUsed(usedUserId)).thenReturn(true);

        // then
        assertThatThrownBy(() -> userService.signup(userDto))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가입되지 않은 회원의 프로필은 조회할 수 없다.")
    void testReadUserWithUnusedUserId() {
        // given
        String unusedUserId = "test";

        // when
        Mockito.when(userRepository.isUserIdUsed(unusedUserId)).thenReturn(false);

        // then
        assertThatThrownBy(() -> userService.getUserByUserId(unusedUserId))
            .isExactlyInstanceOf(ResponseStatusException.class);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "abcde"})
    @DisplayName("개인정보 변경에서도 비밀번호는 6자 이상이어야 한다.")
    void testUpdateUserWithShortPassword(String password) {
        // given
        String userId = "test";
        String uid = "1";

        // when
        UserDto userDto = new UserDto(userId, password, "test", "test@test.com");

        // then
        assertThatThrownBy(() -> userService.updateUser(userDto))
            .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("가입되지 않은 회원의 정보는 변경할 수 없다.")
    void testUpdateUserWithUnusedUserId() {
        // given
        String unusedUserId = "test";

        // when
        UserDto userDto = new UserDto(unusedUserId, "123456", "test", "test@test.com");
        Mockito.when(userRepository.isUserIdUsed(unusedUserId)).thenReturn(false);

        // then
        assertThatThrownBy(() -> userService.updateUser(userDto))
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
