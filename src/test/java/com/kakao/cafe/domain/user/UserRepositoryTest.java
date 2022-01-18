package com.kakao.cafe.domain.user;

import com.kakao.cafe.repository.user.H2UserRepository;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.web.user.dto.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJdbcTest
public class UserRepositoryTest {

    private final UserRepository userRepository;

    @Autowired
    public UserRepositoryTest(JdbcTemplate jdbcTemplate) {
        this.userRepository = new H2UserRepository(jdbcTemplate);
    }

    @DisplayName("저장소 회원정보 저장 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void userSave(UserId userId, Password password, Name name, Email email) {
        //given
        UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        User user = dto.toEntity();

        //when
        userRepository.save(user);

        //then
        User saved = userRepository.findById(userId);
        assertThat(saved).isEqualTo(user);
        assertThat(userRepository.findAll()).isEqualTo(List.of(user));
    }

    private static Stream<Arguments> provideUsers() {
        return Stream.of(
                Arguments.of(new UserId("clo.d"), new Password("1q2w3e4r!Q"), new Name("김동운"), new Email("clo.d@kakaocorp.com"))
        );
    }

    @DisplayName("저장소 회원정보 수정 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void userUpdate(UserId userId, Password password, Name name, Email email) {
        //given
        UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        User user = dto.toEntity();
        userRepository.save(user);

        Name modifiedName = new Name("김동운운");
        Email modifiedEmail = new Email("clo.dd@kakaocorp.com");

        user.setName(modifiedName);
        user.setEmail(modifiedEmail);

        //when
        userRepository.update(userId, password, user);

        //then
        User savedUser = userRepository.findById(userId);

        assertThat(savedUser.getName()).isEqualTo(modifiedName);
        assertThat(savedUser.getEmail()).isEqualTo(modifiedEmail);
    }
}