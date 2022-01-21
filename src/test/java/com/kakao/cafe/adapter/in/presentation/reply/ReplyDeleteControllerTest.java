package com.kakao.cafe.adapter.in.presentation.reply;

import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
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

@WebMvcTest(ReplyDeleteController.class)
class ReplyDeleteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DeleteReplyUseCase deleteReplyUseCase;

    @DisplayName("댓글 삭제 테스트")
    @Test
    void deleteReplyTest() throws Exception {
        int id = 5;
        int articleId = 10;
        String userId = "kakao";
        String name = "champ";
        String email = "champ@kakao.com";
        UserInfo userInfo = new UserInfo(userId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", userInfo);
        String url = "/articles/" + articleId + "/replies/" + id;

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.delete(url).session(session)
                                         .param("userId", userId)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }
}
