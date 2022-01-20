package com.kakao.cafe.controller.user.advice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.UnexpectedException;
import com.kakao.cafe.controller.advice.GlobalControllerAdvice;
import com.kakao.cafe.controller.user.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("UserControllerAdvice 테스트")
class UserControllerAdviceTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserController userController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userController = mock(UserController.class);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new GlobalControllerAdvice())
                .build();
    }

    @DisplayName("IllegalArgumentException 발생 시 UserControllerAdvice에 의해서 예외 처리")
    @Test
    void handleLoginException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                userController).login(any(), any());

        mockMvc.perform(post("/login"))
                .andExpect(redirectedUrl("/login/form"))
                .andExpect(flash().attributeExists("errorExist"))
                .andExpect(flash().attributeExists("errorMsg"))
                .andReturn();
    }

    @DisplayName("IllegalArgumentException 발생 시 UserControllerAdvice에 의해서 예외 처리")
    @Test
    void handleUpdateException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                userController).updateUserInformation(any(), any());

        mockMvc.perform(post("/users/update"))
                .andExpect(redirectedUrl("/users/update"))
                .andExpect(flash().attributeExists("errorExist"))
                .andExpect(flash().attributeExists("errorMsg"))
                .andReturn();
    }

    @DisplayName("IllegalArgumentException 발생 시 UserControllerAdvice에 의해서 예외 처리")
    @Test
    void handleRegisterException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                userController).register(any());

        mockMvc.perform(post("/users"))
                .andExpect(redirectedUrl("/users/form"))
                .andExpect(flash().attributeExists("errorExist"))
                .andExpect(flash().attributeExists("errorMsg"));
    }

    @DisplayName("예상하지 못한 예외 발생시 초기 화면으로 이동")
    @Test
    void handleUnexpectedException() throws Exception {
        doThrow(new UnexpectedException("unexpected")).when(
                userController).register(any());

        mockMvc.perform(post("/users"))
                .andExpect(redirectedUrl("/"));
    }
}
