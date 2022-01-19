package com.kakao.cafe.controller.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.user.UserService;
import com.kakao.cafe.service.user.dto.UserElementDto;
import com.kakao.cafe.service.user.dto.UserInformationDto;
import com.kakao.cafe.service.user.dto.UserLoginDto;
import com.kakao.cafe.service.user.dto.UserRegisterDto;
import com.kakao.cafe.service.user.dto.UserUpdateDto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

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
                .thenReturn(getUsersElementDto(numberOfUser));
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
    }

    @DisplayName("GET/users/{userId} 테스트")
    @Test
    public void showUserProfile() throws Exception {
        //give
        String userId = "userId";
        User user = new User(
                new UserId("userId"),
                new Password("password"),
                new Name("name"),
                new Email("email")
        );
        when(userService.findUserByUserId(userId)).thenReturn(new UserInformationDto(user));

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("POST /login 테스트")
    @Test
    public void login() throws Exception {
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";
        User user = new User(new UserId(userId), new Password(password), new Name(name),
                new Email(email));
        String content = objectMapper.writeValueAsString(new UserLoginDto(userId, password));

        when(userService.findUserByUserId(userId))
                .thenReturn(new UserInformationDto(user));

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

    @DisplayName("GET /users/update 테스트")
    @Test
    public void showUpdateUserInformation() throws Exception {
        String userId = "userId";
        User user = new User(
                new UserId("userId"),
                new Password("password"),
                new Name("name"),
                new Email("email"));
        session.setAttribute("loginUserId", userId);
        when(userService.findUserByUserId(userId))
                .thenReturn(new UserInformationDto(user));

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
        String content = objectMapper.writeValueAsString(
                new UserUpdateDto(userId, password, name, email));

        mockMvc.perform(post("/users/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .session(session)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    private List<UserElementDto> getUsersElementDto(int number) {
        List<UserElementDto> userElementDtos = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            userElementDtos.add(
                    new UserElementDto(
                            new User(
                                    new UserId("userId" + i),
                                    new Password("password" + i),
                                    new Name("name" + i),
                                    new Email("email + i")
                            ),
                            i));
        }
        return userElementDtos;
    }
}
