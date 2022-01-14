package com.kakao.cafe.module.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static com.kakao.cafe.module.model.dto.UserDtos.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 회원가입() throws Exception {
        UserSignUpDto input = new UserSignUpDto("newUser", "1212", "new user", "new@new.com");

        mockMvc.perform(post("/users")
                        .flashAttr("userSignUpDto", input))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));
    }

    @Test
    void 중복_사용자_회원가입() throws Exception {
        UserSignUpDto input = new UserSignUpDto("DuplicatedUser", "1212", "레인", "dup@dup.com");

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