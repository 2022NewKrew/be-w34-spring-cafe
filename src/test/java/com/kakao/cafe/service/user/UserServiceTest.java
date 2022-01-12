package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of("clo.d", "testPassword", "dongwoon", "clo.d@kakaocorp.com")
        );
    }

    @DisplayName("ID검색 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void searchId(String userId, String password, String name, String email) {
        //given
        final UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        final User givenUser = dto.toEntity();

        given(userRepository.findById(userId)).willReturn(givenUser);

        //when
        User findUser = userService.findById(userId);

        //then
        assertThat(findUser).isEqualTo(givenUser);
    }

    @DisplayName("저장소 회원정보 수정 패스워드 일치, 불일치 체크")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void userUpdateValidPasswordCorrect(String userId, String password, String name, String email) {
        //given
        final UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        final User givenUser = dto.toEntity();

        given(userRepository.findById(userId)).willReturn(givenUser);

        String incorrectPassword = password + "incorrect";
        String modifiedName = "modifiedName";
        String modifiedEmail = "modifiedEmail";

        givenUser.setName(modifiedName);
        givenUser.setEmail(modifiedEmail);

        //then
        assertThatNoException().isThrownBy( () -> userService.update(userId, password, givenUser));

        assertThatIllegalArgumentException().isThrownBy(() -> userService.update(userId, incorrectPassword, givenUser));
    }
}