package com.kakao.cafe.users;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    MemoryUserRepository userRepository;

    @InjectMocks
    UserService userService;

    List<User> users = List.of(
            new User("1234", "1234", "1234", "1234"),
            new User("asdf", "asdf", "asdf", "asdf")
    );

    @Test
    void 중복아이디가아닌지확인() {
        given(userRepository.findByUserId(anyString())).willReturn(Optional.empty());

        boolean actual = userService.isDuplicatedUserId("not_duplicated");

        Assertions.assertThat(actual).isFalse();
    }
}