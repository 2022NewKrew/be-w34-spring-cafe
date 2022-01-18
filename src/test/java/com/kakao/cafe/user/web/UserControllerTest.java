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
// 서블릿 컨테이너를 모킹 (테스트 케이스 실행 시 서블릿 컨테이너를 구동하지 않음
@AutoConfigureMockMvc // 컨트롤러 외의 빈도 등록함
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String URL = "/users";

    /*@BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }*/

    /*@AfterEach()
    void tearDown() {
        userRepository.deleteAll();
        userRepository.save(User.builder().userId("miya.ong").password("1234").name("박예지")
            .email("miya.ong@kakaocorp.com").build());
    }*/

    @Test
    @DisplayName("존재하지 않는 유저 찾기 테스트")
    void userDetail() throws Exception {
        String url = URL + "/unknown_user";

        mockMvc.perform(get(url).contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().is(ErrorCode.USER_NOT_FOUND.getStatus()))
            .andExpect(content().string(ErrorCode.USER_NOT_FOUND.getMessage()))
            .andDo(print());
    }

    @Test
    @DisplayName("userId가 중복된 유저 추가 테스트")
    void duplicatedUserAdd() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "miya.ong");
        params.put("password", "1234");
        params.put("name", "박예지");
        params.put("email", "miya.ong@kakaocorp.com");
        MultiValueMap<String, String> multiValueparams = new LinkedMultiValueMap<>();
        multiValueparams.setAll(params);

        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .params(multiValueparams))
            .andExpect(status().is(ErrorCode.USER_DUPLICATED.getStatus()))
            .andExpect(content().string(ErrorCode.USER_DUPLICATED.getMessage()))
            .andDo(print());
    }

    @Test
    @DisplayName("name이 빈 문자열인 유저 추가 테스트")
    void blankNameUserAdd() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("userId", "new.user");
        params.put("password", "1234");
        params.put("name", "");
        params.put("email", "new.user@kakaocorp.com");
        MultiValueMap<String, String> multiValueparams = new LinkedMultiValueMap<>();
        multiValueparams.setAll(params);

        mockMvc.perform(post(URL).contentType(MediaType.APPLICATION_JSON)
                .params(multiValueparams))
            .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
            .andExpect(content().string("Name is mandatory"))
            .andDo(print());
    }
}
