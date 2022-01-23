package com.kakao.cafe;

import static com.kakao.cafe.fixture.UserFixture.LOGIN_USER;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.kakao.cafe.common.util.SessionUtil;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

public class ControllerTest {

    protected MockMvc mockMvc;
    protected MockHttpSession session = new MockHttpSession();

    @BeforeEach
    void setUp(WebApplicationContext context) {
        mockMvc = MockMvcBuilders.webAppContextSetup(context)
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

    protected void loginUser() {
        session.setAttribute(SessionUtil.SESSION_USER, LOGIN_USER);
    }
}
