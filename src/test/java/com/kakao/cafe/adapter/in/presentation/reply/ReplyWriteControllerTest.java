package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.port.in.WriteReplyUseCase;
import com.kakao.cafe.application.user.dto.UserInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ReplyWriteController.class)
class ReplyWriteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    WriteReplyUseCase writeReplyUseCase;

    @DisplayName("댓글 작성 테스트")
    @Test
    void writeReplyTest() throws Exception {
        int articleId = 5;
        String userId = "kakao";
        String name = "champ";
        String email = "champ@kakao.com";
        String contents = "kakao krew";
        String url = "/articles/" + articleId + "/replies";
        UserInfo userInfo = new UserInfo(userId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", userInfo);

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.post(url).session(session)
                                         .param("userId", userId)
                                         .param("contents", contents)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }
}
