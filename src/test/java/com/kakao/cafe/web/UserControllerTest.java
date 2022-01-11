package com.kakao.cafe.web;

import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.web.dto.UserListResponse;
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
    private UserService userService;

    @DisplayName("회원 목록 출력 테스트")
    @MethodSource("provideUsers")
    @ParameterizedTest
    public void showUsers(List<User> users) throws Exception {
        //given
        String url = "/users";
        given(this.userService.findAll()).willReturn(users);
        List<UserListResponse> provideUsers = users.stream().map(UserListResponse::new).collect(Collectors.toList());

        //when
        MvcResult result = this.mvc.perform(get(url))
                .andExpect(status().isOk())
                .andReturn();

        List<UserListResponse> responseUsers = (List<UserListResponse>) result.getModelAndView().getModelMap().get("users");

        //then
        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

        for(int i = 0; i < users.size(); i++) {
            assertThat(responseUsers.get(i).getUserId()).isEqualTo(provideUsers.get(i).getUserId());
            assertThat(responseUsers.get(i).getName()).isEqualTo(provideUsers.get(i).getName());
            assertThat(responseUsers.get(i).getEmail()).isEqualTo(provideUsers.get(i).getEmail());
        }
    }

    private static Stream<List<User>> provideUsers() {
        User test1 = new User();
        User test2 = new User();
        test1.setUserId("test1");
        test1.setPassword("1234");
        test1.setName("test1Name");
        test1.setEmail("test1@kakaocorp.com");

        test2.setUserId("test2");
        test2.setPassword("5678");
        test2.setName("test2Name");
        test2.setEmail("test2@kakaocorp.com");
        return Stream.of(
                List.of(test1, test2)
        );
    }
}