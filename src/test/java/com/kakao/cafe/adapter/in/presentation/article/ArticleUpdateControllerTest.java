package com.kakao.cafe.adapter.in.presentation.article;

import com.kakao.cafe.application.article.port.in.UpdateArticleUseCase;
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

@WebMvcTest(ArticleUpdateController.class)
class ArticleUpdateControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UpdateArticleUseCase updateArticleUseCase;

    @DisplayName("게시글 수정 테스트")
    @Test
    void updateUserInfo() throws Exception {
        int articleId = 5;
        String userId = "champ";
        String name = "HaChanho";
        String email = "champ@kakao.com";
        String title = "kakao";
        String contents = "Hey Kakao!";
        UserInfo sessionedUser = new UserInfo(userId, name, email);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("sessionedUser", sessionedUser);

        String url = "/articles/" + userId + "/" + articleId + "/form";

        // then
        mockMvc.perform(
                   MockMvcRequestBuilders.post(url).session(session)
                                         .param("title", title)
                                         .param("contents", contents)
                                         .accept(MediaType.TEXT_HTML)
               )
               .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
               .andDo(MockMvcResultHandlers.print());
    }

}
