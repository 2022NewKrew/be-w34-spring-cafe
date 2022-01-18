package com.kakao.cafe.user.presentation;

import com.kakao.cafe.matcher.LambdaMatcher;
import com.kakao.cafe.user.application.JoinService;
import com.kakao.cafe.user.application.LoginService;
import com.kakao.cafe.user.application.SearchUserService;
import com.kakao.cafe.user.application.UpdateUserInfoService;
import com.kakao.cafe.user.data.UsersData;
import com.kakao.cafe.user.domain.entity.User;
import com.kakao.cafe.user.domain.entity.UserInfo;
import com.kakao.cafe.user.presentation.dto.JoinRequest;
import com.kakao.cafe.user.presentation.dto.UpdateUserInfoRequest;
import com.kakao.cafe.user.presentation.dto.UserDto;
import com.kakao.cafe.user.presentation.mapper.JoinRequestToUserConverter;
import com.kakao.cafe.user.presentation.mapper.UpdateUserInfoRequestToUserInfoConverter;
import com.kakao.cafe.user.presentation.mapper.UserToUserDtoConverter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SearchUserService searchUserService;

    @MockBean
    private LoginService loginService;

    @MockBean
    private JoinService joinService;

    @MockBean
    private UpdateUserInfoService updateUserInfoService;

    @Autowired
    private ModelMapper modelMapper;

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
                .andExpect(model().attribute("users",
                        new LambdaMatcher<>((List<UserDto> userDtos)
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
                .andExpect(model().attribute("user", new LambdaMatcher<>(
                        (UserDto userDto)-> userDto.getUserId().equals(joinRequest.getUserId()))
                ));

        verify(joinService, times(1)).save(any(User.class));
    }

    @ParameterizedTest
    @DisplayName("이름 변경 성공")
    @MethodSource("com.kakao.cafe.user.data.UsersData#getUpdateRequests")
    void updateInfo(String userId, UpdateUserInfoRequest updateRequest) throws Exception {
        //given

        //when
        final ResultActions actions = mockMvc.perform(post(String.format("/users/update/%s", userId))
                .param("name", updateRequest.getName())
                .param("email", updateRequest.getEmail())
        ).andDo(print());

        //then
        actions.andExpect(status().is3xxRedirection());

        verify(updateUserInfoService, times(1))
                .updateUserInfo(eq(userId), any(UserInfo.class));
    }


    @TestConfiguration
    static class PresentationConfig {
        @Bean
        List<Converter<?,?>> converters(){
            return List.of(
                    new UserToUserDtoConverter(),
                    new JoinRequestToUserConverter(),
                    new UpdateUserInfoRequestToUserInfoConverter()
            );
        }
    }
}