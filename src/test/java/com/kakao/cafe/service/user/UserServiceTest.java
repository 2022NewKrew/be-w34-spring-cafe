package com.kakao.cafe.service.user;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.web.dto.UserCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @DisplayName("ID검색 테스트")
    @Test
    public void searchId() {
        //given
        final String userId = "clo.d";
        final String password = "1234";
        final String name = "dongwoon";
        final String email = "clo.d@kakaocorp.com";
        final UserCreateRequest dto = new UserCreateRequest(userId, password, name, email);
        final User givenUser = dto.toEntity();

        given(userRepository.findById(userId)).willReturn(givenUser);

        //when
        User findUser = userService.findById(userId);

        //then
        assertThat(findUser).isEqualTo(givenUser);
    }
}