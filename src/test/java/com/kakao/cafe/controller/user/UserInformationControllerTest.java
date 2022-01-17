package com.kakao.cafe.controller.user;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.kakao.cafe.model.user.Email;
import com.kakao.cafe.model.user.Name;
import com.kakao.cafe.model.user.Password;
import com.kakao.cafe.model.user.User;
import com.kakao.cafe.model.user.UserId;
import com.kakao.cafe.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("UserInformationController 테스트")
class UserInformationControllerTest {

    private UserService userService;
    private UserInformationController userInformationController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        userService = mock(UserService.class);
        userInformationController = new UserInformationController(userService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(userInformationController)
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
        when(userService.findUserByUserId(userId)).thenReturn(user);

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(view().name("user/profile"))
                .andExpect(model().attributeExists("user"));
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
