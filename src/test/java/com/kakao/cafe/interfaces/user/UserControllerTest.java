package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @DisplayName("회원 목록 출력")
    @MethodSource("generateUsers")
    @ParameterizedTest
    void userList(List<User> users) throws Exception {
        // given
        final String userListUrl = "/users";
        given(userService.findAllUser()).willReturn(users);

        // when
        ResultActions result = mvc.perform(get(userListUrl));
        List<User> responseUsers = (List<User>) result.andReturn()
                .getModelAndView().getModel().get("users");

        // then
        result.andExpect(status().isOk());
        assertThat(responseUsers)
                .usingRecursiveComparison()
                .isEqualTo(users);
    }

    private static Stream<List<User>> generateUsers() {
        User u1 = User.builder()
                .userId("userid1")
                .password("password1")
                .nickname("nickname1")
                .email("email1")
                .build();
        User u2 = User.builder()
                .userId("userid2")
                .password("password2")
                .nickname("nickname2")
                .email("email2")
                .build();

        return Stream.of(
                List.of(u1, u2)
        );
    }
}
