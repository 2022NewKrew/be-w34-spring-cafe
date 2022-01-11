package com.kakao.cafe.domain.user;

import com.kakao.cafe.web.dto.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserRepositoryTest {

    private final UserRepository userRepository = new InMemoryUserRepository();

    @DisplayName("저장소 회원정보 저장 및 탐색 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void testSave(String userId, String password, String name, String email) {
        //given
        UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        User user = dto.toEntity();

        //when
        userRepository.save(user);

        //then
        assertThat(userRepository.findById(userId)).isEqualTo(user);
        assertThat(userRepository.findAll()).isEqualTo(List.of(user));
    }

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of("clo.d", "testPassword", "dongwoon", "clo.d@kakaocorp.com")
        );
    }
}