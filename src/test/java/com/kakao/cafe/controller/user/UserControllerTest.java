package com.kakao.cafe.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.model.User;
import com.kakao.cafe.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayName("UserController 테스트")
class UserControllerTest {
    private UserService userService;
    private UserController userController;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userController = new UserController(userService);

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
        String userId = "userID";
        when(userService.findUserByUserId(userId))
                .thenReturn(new User("userId", "password", "name", "email"));

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("GET /users/{userId}/update 테스트")
    @Test
    public void showUpdateUserInformation() throws Exception {
        String userId = "userID";
        when(userService.findUserByUserId(userId))
                .thenReturn(new User("userId", "password", "name", "email"));

        mockMvc.perform(get("/users/" + userId + "/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("user/updateForm"))
                .andExpect(model().attributeExists("user"));
    }

    @DisplayName("POST /users/{userId}/update 테스트")
    @Test
    public void updateUserInformation() throws Exception {
        String userId = "userId";
        String password = "password";
        String name = "name";
        String email = "email";

        String content = objectMapper.writeValueAsString(new UserUpdateDto(password, name, email));

        mockMvc.perform(post("/users/" + userId + "/update")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    private List<User> getUsers(int number) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            users.add(
                    new User(
                            "userId" + i,
                            "password" + i,
                            "name" + i,
                            "email + i"
                    )
            );
        }
        return users;
    }
}
