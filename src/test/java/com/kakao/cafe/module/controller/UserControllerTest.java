package com.kakao.cafe.module.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.kakao.cafe.module.model.dto.UserDtos.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext context;

    @Test
    void 회원가입() throws Exception {
        UserSignUpDto input = new UserSignUpDto("newUser", "1212", "new user", "new@new.com");

        mockMvc.perform(post("/users")
                        .flashAttr("userSignUpDto", input))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void 중복_사용자_이름_회원가입() throws Exception {
        UserSignUpDto input = new UserSignUpDto("DuplicatedUser", "1212", "레인", "dup@dup.com");

        mockMvc.perform(post("/users")
                        .flashAttr("userSignUpDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 중복_사용자_유저_아이디_회원가입() throws Exception {
        UserSignUpDto input = new UserSignUpDto("rain", "1212", "중복", "dup@dup.com");

        mockMvc.perform(post("/users")
                        .flashAttr("userSignUpDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 사용자_목록() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("userList"))
                .andExpect(view().name("user/list"));
    }

    @Test
    void 사용자_프로필() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", hasProperty("name", is("레인"))))
                .andExpect(view().name("user/profile"));
    }

    @Test
    void 로그인() throws Exception {
        UserSignInDto input = new UserSignInDto("rain", "1111");

        mockMvc.perform(post("/users/sign-in")
                        .flashAttr("userSignInDto", input))
                .andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute("sessionUser", is(notNullValue())))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void 로그인_실패_존재하지_않는_유저_아이디() throws Exception {
        UserSignInDto input = new UserSignInDto("notExist", "1111");

        mockMvc.perform(post("/users/sign-in")
                        .flashAttr("userSignInDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(request().sessionAttribute("sessionUser", is(nullValue())))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 로그인_실패_비밀번호_불일치() throws Exception {
        UserSignInDto input = new UserSignInDto("rain", "1234");

        mockMvc.perform(post("/users/sign-in")
                        .flashAttr("userSignInDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(request().sessionAttribute("sessionUser", is(nullValue())))
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 로그아웃() throws Exception {
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionUser", new UserDto(100L, "test", "테스트", "test@test.com"));

        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        mockMvc.perform(get("/users/sign-out")
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(request().sessionAttribute("sessionUser", is(nullValue())))
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void 로그인없이_로그아웃_시도() throws Exception {
        mockMvc.perform(get("/users/sign-out"))
                .andExpect(status().isUnauthorized())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 존재하지_않는_사용자() throws Exception {
        mockMvc.perform(get("/users/10"))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 사용자_정보_수정폼() throws Exception {
        mockMvc.perform(get("/users/1/form"))
                .andExpect(status().isOk())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("user"))
                .andExpect(model().attribute("user", hasProperty("name", is("레인"))))
                .andExpect(view().name("user/updateForm"));
    }

    @Test
    void 사용자_정보_수정() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "1111", "2222", "이름 바꿈", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void 사용자_정보_수정시_틀린_비밀번호_입력() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "2222", "2222", "이름 바꿈", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }

    @Test
    void 사용자_정보_수정시_중복_이름_입력() throws Exception {
        UserUpdateDto input = new UserUpdateDto(1L, "1111", "2222", "스노우", "change@c.com");

        mockMvc.perform(put("/users/1")
                        .flashAttr("userUpdateDto", input))
                .andExpect(status().isBadRequest())
                .andExpect(model().size(1))
                .andExpect(model().attributeExists("msg"))
                .andExpect(view().name("infra/error"));
    }
}