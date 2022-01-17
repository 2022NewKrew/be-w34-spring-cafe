package com.kakao.cafe.app.controller;

import com.kakao.cafe.domain.exception.CannotAuthenticateException;
import com.kakao.cafe.domain.exception.DuplicateUserIdException;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@MockBean(UserService.class)
class UserControllerTest {

    @Autowired
    private UserService service;

    @Autowired
    private MockMvc mvc;

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
        assertEquals(id, session.getAttribute("currentUserId"));
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
        assertNull(session.getAttribute("currentUserId"));
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
    void profile_me_withSession() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("currentUserId", 1L);
        UserDto user = new UserDto.Builder()
                .id(1L)
                .name("name")
                .email("test@example.com")
                .build();
        when(service.get(1L)).thenReturn(Optional.of(user));

        mvc.perform(get("/users/me").session(session))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void profile_me_withoutSession() throws Exception {
        when(service.get(anyLong())).thenReturn(Optional.empty());

        mvc.perform(get("/users/me"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void profile_id_existing() throws Exception {
        long id = 1L;
        UserDto user = new UserDto.Builder()
                .id(id)
                .name("name")
                .email("test@example.com")
                .build();
        when(service.get(id)).thenReturn(Optional.of(user));

        mvc.perform(get("/users/" + id))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void profile_id_notFound() throws Exception {
        long id = 1L;
        when(service.get(anyLong())).thenReturn(Optional.empty());

        mvc.perform(get("/users/" + id))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void profile_wrongPath() throws Exception {
        mvc.perform(get("/users/wrong"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void login() throws Exception {
        long id = 123L;
        UserDto user = new UserDto.Builder().id(id).build();
        when(service.login(any())).thenReturn(user);
        MockHttpSession session = new MockHttpSession();

        mvc.perform(post("/user/login").session(session))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        assertEquals(id, session.getAttribute("currentUserId"));
    }

    @Test
    void login_failed() throws Exception {
        MockHttpSession session = new MockHttpSession();
        when(service.login(any())).thenAnswer(invocation -> {
            throw new CannotAuthenticateException();
        });

        mvc.perform(post("/user/login").session(session))
                .andDo(print())
                .andExpect(status().isBadRequest());
        //noinspection ConstantConditions
        assertNull(session.getAttribute("currentUserId"));
    }
}
