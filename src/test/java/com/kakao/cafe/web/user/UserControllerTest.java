package com.kakao.cafe.web.user;

import com.kakao.cafe.domain.user.*;
import com.kakao.cafe.service.user.UserCreateService;
import com.kakao.cafe.service.user.UserFindService;
import com.kakao.cafe.service.user.UserUpdateService;
import com.kakao.cafe.web.user.dto.UserListResponse;
import com.kakao.cafe.web.user.dto.UserResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserFindService userFindService;

    @MockBean
    private UserCreateService userCreateService;

    @MockBean
    private UserUpdateService userUpdateService;

    @DisplayName("회원 목록 출력 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void showUsers(List<User> users) throws Exception {
        //given
        String url = "/users";
        given(this.userFindService.findAll()).willReturn(users);
        UserListResponse provideUsers = new UserListResponse(
                users.stream()
                        .map(UserResponse::new)
                        .collect(Collectors.toList())

        );

        //when
        MvcResult result = this.mvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        UserListResponse responseUsers = (UserListResponse) result.getModelAndView().getModelMap().get("users");

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(responseUsers.equals(provideUsers)).isTrue();
    }

    private static Stream<List<User>> provideUsers() {
        User test1 = new User();
        User test2 = new User();
        test1.setUserId(new UserId("clo.d"));
        test1.setPassword(new Password("1q2w3e4r!Q"));
        test1.setName(new Name("김동운"));
        test1.setEmail(new Email("clo.d@kakaocorp.com"));

        test2.setUserId(new UserId("clo.dd"));
        test2.setPassword(new Password("1q2w3e4r@W"));
        test2.setName(new Name("김동운운"));
        test2.setEmail(new Email("clo.dd@kakaocorp.com"));
        return Stream.of(
                List.of(test1, test2)
        );
    }

    @DisplayName("개인정보 수정화면 연결 확인")
    @MethodSource("provideUser")
    @ParameterizedTest
    public void getModifyUserForm(User user) throws Exception {
        String url = "/users/" + user.getUserId().getValue() + "/form";
        given(this.userFindService.findById(user.getUserId())).willReturn(user);

        mvc.perform(get(url).param("userId", user.getUserId().getValue()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
    }

    private static Stream<User> provideUser() {
        User test = new User();
        test.setUserId(new UserId("clo.d"));
        test.setPassword(new Password("1q2w3e4r!Q"));
        test.setName(new Name("김동운"));
        test.setEmail(new Email("clo.d@kakaocorp.com"));
        return Stream.of( test );
    }
}