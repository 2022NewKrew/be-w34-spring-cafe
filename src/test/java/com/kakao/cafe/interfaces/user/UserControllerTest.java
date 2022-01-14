package com.kakao.cafe.interfaces.user;

import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.application.user.UpdateUserService;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.interfaces.user.dto.response.UserResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindUserService findUserService;

    @MockBean
    private SignUpUserService signUpUserService;

    @MockBean
    private UpdateUserService updateUserService;

    private static Stream<List<User>> createUserList() {
        return Stream.of(
                List.of(
                        new User("483759", "password", "윤이진", "483759@naver.com"),
                        new User("id", "password", "name", "email@kakao.com")
                        )
        );
    }

    @DisplayName("모든 유저의 목록을 조회할 수 있다.")
    @MethodSource("createUserList")
    @ParameterizedTest
    void getAllUsers(List<User> userList) throws Exception {
        // given
        List<UserResponseDto> userResponseDtoList = List.of(
                new UserResponseDto("483759", "윤이진", "483759@naver.com"),
                new UserResponseDto("id", "name", "email@kakao.com")
        );
        given(findUserService.findAllUser())
                .willReturn(userList);

        // when
        ResultActions actions = mockMvc.perform(get("/users"))
                .andDo(print());
        List<UserResponseDto> responseUserList = (List<UserResponseDto>) actions.andReturn()
                .getModelAndView().getModelMap()
                .get("users");

        //then
        actions
                .andExpect(status().isOk());
        assertThat(responseUserList)
                .usingRecursiveComparison()
                .isEqualTo(userResponseDtoList);
    }
}