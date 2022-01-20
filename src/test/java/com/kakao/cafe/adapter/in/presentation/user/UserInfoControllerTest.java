package com.kakao.cafe.adapter.in.presentation.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.application.user.dto.UserInfoList;
import com.kakao.cafe.application.user.port.in.GetUserInfoUseCase;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserInfoController.class)
class UserInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserInfoUseCase getUserInfoUseCase;

    @DisplayName("회원 목록 출력 테스트")
    @Test
    void displayUserList() throws Exception {
        // given
        String url = "/users";
        List<UserInfo> givenUserInfoList = new ArrayList<>();
        givenUserInfoList.add(new UserInfo("kakao", "krew", "kakao@kakao.com"));
        givenUserInfoList.add(new UserInfo("champ", "HaChanho", "champ"));
        given(getUserInfoUseCase.getAllUsersInfo()).willReturn(UserInfoList.from(givenUserInfoList));

        // when
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
                                  .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print())
                                  .andReturn();

        List<UserInfo> responseUserList = (List<UserInfo>) Objects.requireNonNull(result.getModelAndView())
                                                                  .getModelMap().get("users");

        // then
        for (int i = 0; i < givenUserInfoList.size(); i++) {
            assertThat(givenUserInfoList.get(i)).isEqualTo(responseUserList.get(i));
        }
    }

    @DisplayName("개인 프로필 출력 테스트")
    @Test
    void displayUserProfile() throws Exception {
        // given
        String userId = "champ";
        String name = "HaChanho";
        String email = "champ@kakao.com";
        UserInfo givenUserInfo = new UserInfo(userId, name, email);
        String url = "/users/" + userId;
        given(getUserInfoUseCase.getUserProfile(userId)).willReturn(givenUserInfo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(url).accept(MediaType.TEXT_HTML))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attribute("name", name))
               .andExpect(MockMvcResultMatchers.model().attribute("email", email))
               .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("개인 정보 변경 화면 출력 성공 테스트")
    @Test
    void displayUpdateUserInfoSuccess() throws Exception {
        String userId = "champ";
        String name = "HaChanho";
        String email = "champ@kakao.com";
        UserInfo givenUserInfo = new UserInfo(userId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", givenUserInfo);
        String url = "/users/" + userId + "/form";
        given(getUserInfoUseCase.getUserProfile(userId)).willReturn(givenUserInfo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(url).session(session).accept(MediaType.TEXT_HTML))
               .andExpect(MockMvcResultMatchers.status().isOk())
               .andExpect(MockMvcResultMatchers.model().attribute("user", givenUserInfo))
               .andDo(MockMvcResultHandlers.print());
    }

    @DisplayName("개인 정보 변경 권한 오류 테스트")
    @Test
    void displayUpdateUserInfoFail() throws Exception {
        String sessionedUserId = "kakao";
        String userId = "champ";
        String name = "HaChanho";
        String email = "champ@kakao.com";
        UserInfo givenUserInfo = new UserInfo(userId, name, email);
        UserInfo sessionedUser = new UserInfo(sessionedUserId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", sessionedUser);
        String url = "/users/" + userId + "/form";
        given(getUserInfoUseCase.getUserProfile(userId)).willReturn(givenUserInfo);

        // then
        mockMvc.perform(MockMvcRequestBuilders.get(url).session(session).accept(MediaType.TEXT_HTML))
               .andExpect(MockMvcResultMatchers.status().is4xxClientError())
               .andDo(MockMvcResultHandlers.print());
    }
}
