package com.kakao.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.user.controller.UserController;
import com.kakao.cafe.user.dto.UserProfileDto;
import com.kakao.cafe.user.dto.UserRequest;
import com.kakao.cafe.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = {UserController.class})
@DisplayName("User 컨트롤러 테스트")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @DisplayName("[GET] /user/list 테스트")
    public void getUserListsTest() throws Exception {
        mockMvc.perform(get("/user/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"grimm", "grimm2"})
    @DisplayName("[GET] /user/profile/{userId} 테스트")
    public void getUserProfileTest(String userId) throws Exception{
        UserProfileDto userProfileDto = new UserProfileDto(userId, userId + "@email.com");

        when(userService.getUserProfile(userId)).thenReturn(userProfileDto);

        mockMvc.perform(get("/user/profile/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("/user/profile"))
                .andExpect(model().attributeExists("profile"));
    }

    @ParameterizedTest
    @MethodSource("getUserRequests")
    @DisplayName("[POST] /user/create 테스트")
    public void signUpTest (UserRequest userRequest) throws Exception{
        String content = objectMapper.writeValueAsString(userRequest);

        mockMvc.perform(post("/user/create")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/user/list"));
    }

    private static Stream<UserRequest> getUserRequests(){
        return Stream.of(
                new UserRequest("grimm", "그림", "12341234", "grimm@kaakocorp.com"),
                new UserRequest("grimm2", "그림2", "12341234", "grimm@kaakocorp.com"),
                new UserRequest("grimm3", "그림3", "12341234", "grimm@kaakocorp.com")
        );
    }
}
