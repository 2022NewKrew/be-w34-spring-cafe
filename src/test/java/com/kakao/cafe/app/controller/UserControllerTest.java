package com.kakao.cafe.app.controller;

import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@WebMvcTest(UserController.class)
@MockBean(UserService.class)
class UserControllerTest {

    @Autowired
    private UserService service;

    private MockMvc mvc;

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(sharedHttpSession())
                .build();
    }

    @Test
    void create() throws Exception {
        long id = 1234L;
        when(service.create(any())).thenReturn(new UserDto.Builder().id(id).build());
        MockHttpSession session = new MockHttpSession();

        MockHttpServletRequestBuilder request = get("/user/create")
                .session(session)
                .param("userId", "test")
                .param("name", "test")
                .param("email", "email@example.com")
                .param("password", "password");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        assertEquals(id, session.getAttribute("id"));
    }

    @Test
    void create_nameTooShort() throws Exception {
        when(service.create(any())).thenReturn(null);
        MockHttpSession session = new MockHttpSession();

        MockHttpServletRequestBuilder request = get("/user/create")
                .session(session)
                .param("userId", "test")
                .param("name", "")
                .param("email", "email@example.com")
                .param("password", "password");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
        //noinspection ConstantConditions
        assertNull(session.getAttribute("id"));
    }

    @Test
    void create_nameDuplicate() throws Exception {
        when(service.create(any())).thenAnswer(invocation -> {
            throw new DuplicateUserIdException();
        });
        MockHttpSession session = new MockHttpSession();

        MockHttpServletRequestBuilder request = get("/user/create")
                .session(session)
                .param("userId", "test")
                .param("name", "name")
                .param("email", "email@example.com")
                .param("password", "password");
        mvc.perform(request)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void list() throws Exception {
        List<UserDto> users = List.of(
                new UserDto.Builder()
                        .id(1L)
                        .name("name1")
                        .email("name1@example.com")
                        .build(),
                new UserDto.Builder()
                        .id(2L)
                        .name("name2")
                        .email("name2@example.com")
                        .build(),
                new UserDto.Builder()
                        .id(3L)
                        .name("name3")
                        .email("name3@example.com")
                        .build()
        );
        when(service.list()).thenReturn(users);

        mvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name1")))
                .andExpect(content().string(containsString("name2")));
    }

    @Test
    void profile() {
        throw new RuntimeException("not implemented");
    }

    @Test
    void login() {
        throw new RuntimeException("not implemented");
    }
}
