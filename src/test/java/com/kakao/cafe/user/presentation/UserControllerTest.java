package com.kakao.cafe.user.presentation;

import com.kakao.cafe.ControllerTest;
import com.kakao.cafe.matcher.LambdaMatcher;
import com.kakao.cafe.user.data.UsersData;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;

import static com.kakao.cafe.matcher.LambdaMatcher.lambdaMatcher;
import static java.util.stream.Collectors.toSet;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UserControllerTest extends ControllerTest{
    @Test
    @DisplayName("사용자 목록 보여주기 성공")
    void listUsers() throws Exception {
        //given
        final List<User> users = UsersData.getUserList();
        given(searchUserService.getAllUsers()).willReturn(users);

        //when
        final ResultActions actions
                = mockMvc.perform(get("/users"))
                .andDo(print());

        //then
        final Set<String> userIds = users.stream().map(User::getUserId).collect(toSet());
        actions.andExpect(status().isOk())
                .andExpect(model().attribute("users", lambdaMatcher((List<UserDto> userDtos)
                                -> userDtos.stream().map(UserDto::getUserId).allMatch(userIds::contains))
                ));
    }

    @ParameterizedTest
    @DisplayName("가입 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getJoinRequests")
    void join(JoinRequest joinRequest) throws Exception {
        //given

        //when
        final ResultActions actions = mockMvc.perform(post("/users")
                        .param("userId", joinRequest.getUserId())
                        .param("password", joinRequest.getPassword())
                        .param("name", joinRequest.getName())
                        .param("email", joinRequest.getEmail())
        ).andDo(print());

        //then
        actions.andExpect(status().isOk())
                .andExpect(model().attribute("user", lambdaMatcher((UserDto userDto)
                        -> userDto.getUserId().equals(joinRequest.getUserId()))
                ));

        verify(joinService, times(1)).save(argThat(
                user -> user.getUserId().equals(joinRequest.getUserId())
                        && user.getPassword().equals(joinRequest.getPassword())
        ));
    }

    @ParameterizedTest
    @DisplayName("이름 변경 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUpdateRequests")
    void updateInfo(String userId, UpdateUserInfoRequest updateRequest) throws Exception {
        //given
        final MockHttpSession session = createMockSession(userId);

        //when
        final ResultActions actions = mockMvc.perform(post("/users/update")
                .session(session)
                .param("name", updateRequest.getName())
                .param("email", updateRequest.getEmail())
        ).andDo(print());

        //then
        actions.andExpect(status().is3xxRedirection());

        verify(updateUserInfoService, times(1))
                .updateUserInfo(eq(userId), argThat(
                        userinfo -> userinfo.getName().equals(updateRequest.getName())
                                && userinfo.getEmail().equals(updateRequest.getEmail())
                ));
    }

    private static MockHttpSession createMockSession(String userId){
        final MockHttpSession session = new MockHttpSession();
        session.setAttribute("userId", userId);
        return session;
    }
}