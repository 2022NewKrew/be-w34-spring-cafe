package com.kakao.cafe.user.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.kakao.cafe.exception.ErrorCode;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("존재하지 않는 유저 찾기 테스트")
    void userDetail() throws Exception {
        String url = "/users/unknown_user";

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(ErrorCode.USER_NOT_FOUND.getStatus()))
            .andExpect(content().string(ErrorCode.USER_NOT_FOUND.getMessage()))
            .andDo(print());
    }

    @Test
    @DisplayName("userId가 중복된 유저 추가 테스트")
    void duplicatedUserAdd() throws Exception {
        String url = "/users";
        Map<String, String> params = new HashMap<>();
        params.put("userId", "miya.ong");
        params.put("password", "1234");
        params.put("name", "박예지");
        params.put("email", "miya.ong@kakaocorp.com");
        MultiValueMap<String, String> multiValueparams = new LinkedMultiValueMap<>();
        multiValueparams.setAll(params);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .params(multiValueparams))
            .andExpect(status().is(ErrorCode.USER_DUPLICATED.getStatus()))
            .andExpect(content().string(ErrorCode.USER_DUPLICATED.getMessage()))
            .andDo(print());
    }

    @Test
    @DisplayName("name이 빈 문자열인 유저 추가 테스트")
    void blankNameUserAdd() throws Exception {
        String url = "/users";
        Map<String, String> params = new HashMap<>();
        params.put("userId", "new.user");
        params.put("password", "1234");
        params.put("name", "");
        params.put("email", "new.user@kakaocorp.com");
        MultiValueMap<String, String> multiValueparams = new LinkedMultiValueMap<>();
        multiValueparams.setAll(params);

        mockMvc.perform(post(url).contentType(MediaType.APPLICATION_JSON)
                .params(multiValueparams))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
            .andExpect(content().string("Name is mandatory"))
            .andDo(print());
    }
}
