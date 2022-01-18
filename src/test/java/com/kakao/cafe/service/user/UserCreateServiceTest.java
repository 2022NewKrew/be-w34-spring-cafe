package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.*;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.web.user.dto.UserCreateRequest;
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
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserCreateServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserCreateService userCreateService;

    @DisplayName("회원정보 생성, 이미 존재하는 id인지 체크")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void userCreateValidPasswordCorrect(UserId userId, Password password, Name name, Email email) {
        //given
        final UserId newUserId = new UserId("clo.dd");
        final UserCreateRequest givenUserDto = new UserCreateRequest(userId, password, name, email);
        final UserCreateRequest newUserDto = new UserCreateRequest(newUserId, password, name, email);

        final User givenUser = givenUserDto.toEntity();
        final User newUser = newUserDto.toEntity();

        given(userRepository.findById(userId)).willReturn(givenUser);
        given(userRepository.findById(newUserId)).willReturn(null);

        //then
        assertThatNoException().isThrownBy( () -> userCreateService.create(newUser));
        assertThatIllegalArgumentException().isThrownBy(() -> userCreateService.create(givenUser));
    }

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of(new UserId("clo.d"), new Password("1q2w3e4r!Q"), new Name("김동운"), new Email("clo.d@kakaocorp.com"))
        );
    }
}