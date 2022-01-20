package com.kakao.cafe.controller.reply;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kakao.cafe.service.reply.ReplyService;
import com.kakao.cafe.service.reply.dto.ReplyCreateDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@DisplayName("ReplyController 테스트")
class ReplyControllerTest {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ReplyService replyService;
    private ReplyController replyController;
    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    private void before() {
        replyService = mock(ReplyService.class);
        replyController = new ReplyController(replyService);
        session = new MockHttpSession();

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        mockMvc = MockMvcBuilders.standaloneSetup(replyController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @DisplayName("POST /replies 테스트")
    @Test
    void postReplyTest() throws Exception {
        ReplyCreateDto replyCreateDto = new ReplyCreateDto(1, "userId", "comment");
        String content = objectMapper.writeValueAsString(replyCreateDto);
        session.setAttribute("loginUserId", "userId");
        mockMvc.perform(post("/replies?id=1&articleId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles?id=1"));
    }

    @DisplayName("DELETE /replies 테스트")
    @Test
    void deleteTest() throws Exception {
        ReplyCreateDto replyCreateDto = new ReplyCreateDto(1, "userId", "comment");
        String content = objectMapper.writeValueAsString(replyCreateDto);
        session.setAttribute("loginUserId", "userId");
        mockMvc.perform(delete("/replies?id=1&replyId=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
                        .session(session))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/articles?id=1"));
    }
}
