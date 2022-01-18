package com.kakao.cafe.controller.user;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.user.dto.UserRegisterDto;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("UserRegisterController 테스트")
class UserRegisterControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserService userService;
    private UserRegisterController userRegisterController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userRegisterController = new UserRegisterController(userService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userRegisterController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("POST /users 테스트")
    @Test
    public void register() throws Exception {
        String content = objectMapper.writeValueAsString(
                new UserRegisterDto("userId", "password", "name", "email"));

        mockMvc.perform(
                        post("/users")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }
}
