package com.kakao.cafe;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
<<<<<<< HEAD
<<<<<<< HEAD
    @DisplayName("GET /user 요청 확인 - 회원 목록 가져오기")
    void getUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
=======
    @DisplayName("GET /list 요청 확인 - 회원 목록 가져오기")
    void userTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/list"))
>>>>>>> 374a4c5 (H2 DB 연결 없이 기능 구현 완료(로그인 기능 x))
=======
    @DisplayName("GET /user 요청 확인 - 회원 목록 가져오기")
    void getUserTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user"))
>>>>>>> 31bc618 (Test code 수정)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
