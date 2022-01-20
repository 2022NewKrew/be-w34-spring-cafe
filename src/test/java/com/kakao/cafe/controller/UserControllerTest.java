package com.kakao.cafe.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.dto.UserProfileDto;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {
    private static final UserProfileDto user = new UserProfileDto("userId", "email", "name");
    private static final String testUserId = "userId";
    private static final String testNotExistUserId = "notExistId";

    private ObjectMapper objectMapper = new ObjectMapper();
    private UserService userService;
    private UserController userController;
    private MockHttpSession mockHttpSession;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        mockHttpSession = new MockHttpSession();
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();

        when(userService.findById(testUserId)).thenReturn(user);
        when(userService.findById(testNotExistUserId)).thenThrow(NoSuchElementException.class);
    }

    @DisplayName("POST /create 회원가입 테스트")
    @Test
    public void createTest() throws Exception {
        UserDto userDto = new UserDto(testUserId, "email", "name", "password");
        String content = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/users/create")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl( "/list"));
    }

    @DisplayName("Get /list 유저 리스트 반환 테스트")
    @Test
    public void getUserListTest() throws Exception {
        when(userService.getUserList()).thenReturn(new ArrayList<UserProfileDto>());

        mockMvc.perform(get("/users/list"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("users"))
                .andExpect(view().name("user/list"));
    }

    @DisplayName("Get /{userId} 프로필 화면 표시 - 유저가 존재하는 case")
    @Test
    public void getUserProfileTest() throws Exception {
        String testUrl = String.format("/users/%s", testUserId);

        mockMvc.perform(get(testUrl))
                .andExpect(model().attributeExists("user"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"));
    }

    @DisplayName("Get /{userId} 프로필 화면 표시 - 유저가 존재하지 않는 case")
    @Test
    public void getUserProfileNotExistTest() throws Exception {
        String testUrl = String.format("/users/%s", testNotExistUserId);

        mockMvc.perform(get(testUrl))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("Get /{userId}/form 특정 사용자 정보 수정 화면으로 이동")
    @Test
    public void updateFormTest() throws Exception {
        String testUrl = String.format("/users/%s/form", testUserId);
        UserProfileDto user = new UserProfileDto(testUserId, "email", "name");

        when(userService.checkSessionUser(testUserId, mockHttpSession)).thenReturn(true);

        mockMvc.perform(get(testUrl)
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/updateForm"));
    }

    @DisplayName("Get /{userId}/form 특정 사용자 정보 수정 화면으로 이동 - 유저가 존재하지 않는 case")
    @Test
    public void updateFormUserNotExistTest() throws Exception {
        String testUrl = String.format("/users/%s/form", "notExistId");

        mockMvc.perform(get(testUrl))
                .andExpect(status().isFound())
                .andExpect(redirectedUrl("/"));
    }
}
