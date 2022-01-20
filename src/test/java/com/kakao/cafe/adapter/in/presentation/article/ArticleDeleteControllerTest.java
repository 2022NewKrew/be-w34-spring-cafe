package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.reply.port.in.DeleteReplyUseCase;
import com.kakao.cafe.application.reply.port.in.GetRepliesUseCase;
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

@WebMvcTest(ArticleDeleteController.class)
class ArticleDeleteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    DeleteArticleUseCase deleteArticleUseCase;

    @MockBean
    DeleteReplyUseCase deleteReplyUseCase;

    @MockBean
    GetRepliesUseCase getRepliesUseCase;

    @DisplayName("게시글 삭제 테스트")
    @Test
    void deleteArticleTest() throws Exception {
        int id = 5;
        String userId = "kakao";
        String name = "champ";
        String email = "champ@kakao.com";
        UserInfo userInfo = new UserInfo(userId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", userInfo);
        String url = "/articles/" + id + "/delete";

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
