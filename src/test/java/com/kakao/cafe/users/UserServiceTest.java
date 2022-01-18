package com.kakao.cafe.users;

import com.kakao.cafe.CafeApplicationTests;
import com.kakao.cafe.exceptions.BadCredentialException;
import com.kakao.cafe.exceptions.NotFoundException;
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
class UserServiceTest extends CafeApplicationTests {
    @Mock
    MemoryUserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void 회원가입() {
        User given = users.get(0);
        given(userRepository.findByUserId(any())).willReturn(Optional.empty());
        given(userRepository.save(any())).willReturn(given);

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setUserId(given.getUserId());
        signUpRequest.setName(given.getName());
        signUpRequest.setEmail(given.getEmail());
        signUpRequest.setPassword(given.getPassword());

        UserDto userDto = userService.signUp(signUpRequest);

        Assertions.assertThat(signUpRequest.getEmail()).isEqualTo(userDto.getEmail());
        Assertions.assertThat(signUpRequest.getName()).isEqualTo(userDto.getName());
        Assertions.assertThat(signUpRequest.getUserId()).isEqualTo(userDto.getUserId());
    }

    @Test
    void 사용자리스트가져오기() {
        given(userRepository.findAll()).willReturn(users);

        List<UserDto> userDtoList = userService.getUsers();

        Assertions.assertThat(userDtoList).hasSize(users.size());
    }

    @Test
    void 로그인성공() {
        given(userRepository.findByUserId(anyString())).willReturn(Optional.of(users.get(0)));

        UserDto actual = userService.login(users.get(0).getUserId(), users.get(0).getPassword());

        Assertions.assertThat(actual.getUserId()).isEqualTo(users.get(0).getUserId());
        Assertions.assertThat(actual.getEmail()).isEqualTo(users.get(0).getEmail());
        Assertions.assertThat(actual.getName()).isEqualTo(users.get(0).getName());
    }

    @Test
    void 로그인할때_아이디존재안할때() {
        given(userRepository.findByUserId(anyString())).willReturn(Optional.empty());

        Assertions.assertThatThrownBy(() -> userService.login(users.get(0).getUserId(), users.get(0).getPassword())).isInstanceOf(NotFoundException.class);
    }

    @Test
    void 로그인할때_비번틀릴때() {
        User given = users.get(0);
        String badPassword = "bad";

        given(userRepository.findByUserId(anyString())).willReturn(Optional.of(given));

        Assertions.assertThatThrownBy(() -> userService.login(given.getUserId(), badPassword)).isInstanceOf(BadCredentialException.class);
    }

    @Test
    void 중복아이디가아닌지확인() {
        given(userRepository.findByUserId(anyString())).willReturn(Optional.empty());

        boolean actual = userService.isDuplicatedUserId("not_duplicated");

        Assertions.assertThat(actual).isFalse();
    }
}