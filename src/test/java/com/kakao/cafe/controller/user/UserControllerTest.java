package com.kakao.cafe.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.model.user.*;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("UserController 테스트")
class UserControllerTest {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private UserService userService;
    private UserController userController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userController = new UserController(userService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("GET /users 테스트")
    @Test
    public void showUsers() throws Exception {
        int numberOfUser = 10;
        when(userService.getUsers())
                .thenReturn(getUsers(numberOfUser));
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @DisplayName("POST /users 테스트")
    @Test
    public void register() throws Exception {
        String content = objectMapper.writeValueAsString(new UserRegisterDto("userId", "password", "name", "email"));

        mockMvc.perform(
                        post("/users")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @DisplayName("GET/users/{userId} 테스트")
    @Test
    public void showUserProfile() throws Exception {
        //give
        String userId = "userId";
        when(userService.findUserByUserId(userId))
                .thenReturn(
                        new User(
                                new UserId("userId"),
                                new Password("password"),
                                new Name("name"),
                                new Email("email")
                        )
                );

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("GET /users/update 테스트")
    @Test
    public void showUpdateUserInformation() throws Exception {
        String userId = "userId";
        session.setAttribute("userId", userId);
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

        session.setAttribute("userId", userId);
        String content = objectMapper.writeValueAsString(new UserUpdateDto(password, name, email));

        mockMvc.perform(post("/users/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("POST /users/login 테스트")
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
        when(userService.hasUser(userId, password))
                .thenReturn(true);

        mockMvc.perform(post("/users/login")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @DisplayName("GET /users/logout 테스트, session이 없을 때 예외를 던진다.")
    @Test
    public void illegalLogout() {
        //give
        session.invalidate();

        //when
        //then
        assertThatThrownBy(() -> mockMvc.perform(get("/users/logout").session(session)))
                .isInstanceOf(HttpSessionRequiredException.class);
    }

    @DisplayName("GET /users/logout 테스트, session이 있을 때 예외를 던지지 않는다..")
    @Test
    public void legalLogout() {
        //give
        //when
        //then
        assertThatCode(() -> mockMvc.perform(get("/users/logout").session(session)))
                .doesNotThrowAnyException();
        assertThat(session.isInvalid()).isTrue();
    }

    private List<User> getUsers(int number) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            users.add(
                    new User(
                            new UserId("userId" + i),
                            new Password("password" + i),
                            new Name("name" + i),
                            new Email("email + i")
                    )
            );
        }
        return users;
    }
}
