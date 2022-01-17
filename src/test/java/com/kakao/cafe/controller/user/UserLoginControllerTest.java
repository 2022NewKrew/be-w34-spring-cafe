package com.kakao.cafe.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.user.advice.UserLoginControllerAdvice;
import com.kakao.cafe.controller.user.dto.UserLoginDto;
import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("UserLoginController 테스트")
class UserLoginControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserService userService;
    private UserLoginController userLoginController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userLoginController = new UserLoginController(userService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userLoginController)
                .setViewResolvers(viewResolver)
                .setControllerAdvice(new UserLoginControllerAdvice())
                .build();
    }

    @DisplayName("POST /login 테스트")
    @Test
    public void login() throws Exception {
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        String content = objectMapper.writeValueAsString(new UserLoginDto(userId, password));

        when(userService.findUserByUserId(userId))
                .thenReturn(
                        new User(
                                new UserId(userId),
                                new Password(password),
                                new Name(name),
                                new Email(email)
                        )
                );

        mockMvc.perform(post("/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("GET /logout 테스트")
    @Test
    public void Logout() {
        //give
        //when
        //then
        assertThatCode(() -> mockMvc.perform(get("/logout").session(session)))
                .doesNotThrowAnyException();
        assertThat(session.isInvalid()).isTrue();
    }
}
