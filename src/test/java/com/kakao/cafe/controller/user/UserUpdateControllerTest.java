package com.kakao.cafe.controller.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.controller.user.dto.UserUpdateDto;
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

@DisplayName("UserUpdateController 테스트")
class UserUpdateControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserService userService;
    private UserUpdateController userUpdateController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userUpdateController = new UserUpdateController(userService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userUpdateController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("GET /users/update 테스트")
    @Test
    public void showUpdateUserInformation() throws Exception {
        String userId = "userId";
        session.setAttribute("loginUserId", userId);
        when(userService.findUserByUserId(userId))
                .thenReturn(
                        new User(
                                new UserId("userId"),
                                new Password("password"),
                                new Name("name"),
                                new Email("email")
                        )
                );

        mockMvc.perform(get("/users/update")
                        .session(session))
                .andExpect(status().isOk())
                .andExpect(view().name("user/updateForm"));
    }

    @DisplayName("POST /users/update 테스트")
    @Test
    public void updateUserInformation() throws Exception {
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";

        session.setAttribute("loginUserId", userId);
        String content = objectMapper.writeValueAsString(new UserUpdateDto(name, email));

        mockMvc.perform(post("/users/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }
}
