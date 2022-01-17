package com.kakao.cafe.controller.user.advice;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.user.UserUpdateController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("UserUpdateControllerAdvice 테스트")
class UserUpdateControllerAdviceTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserUpdateController userUpdateController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userUpdateController = mock(UserUpdateController.class);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userUpdateController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new UserUpdateControllerAdvice())
                .build();
    }

    @DisplayName("IllegalArgumentException 발생 시 UserUpdateControllerAdvice에 의해서 예외 처리")
    @Test
    void handleIllegalArgumentException() throws Exception {
        doThrow(new IllegalArgumentException("Exception")).when(
                userUpdateController).updateUserInformation(any(), any(), any());

        mockMvc.perform(post("/users/update"))
                .andExpect(view().name("user/updateForm"))
                .andExpect(model().attributeExists("errorExist"))
                .andExpect(model().attributeExists("errorMsg"))
                .andReturn();
    }
}
