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

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserFindServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserFindService userFindService;


    @DisplayName("ID검색 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void findUserId(UserId userId, Password password, Name name, Email email) {
        //given
        final UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        final User givenUser = dto.toEntity();

        given(userRepository.findById(userId)).willReturn(givenUser);

        //when
        User findUser = userFindService.findById(userId);

        //then
        assertThat(findUser).isEqualTo(givenUser);
    }

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of(new UserId("clo.d"), new Password("testPassword"), new Name("dongwoon"), new Email("clo.d@kakaocorp.com"))
        );
    }
}