package com.kakao.cafe.controller;

import com.kakao.cafe.advice.LoginUserMethodArgumentResolver;
import com.kakao.cafe.dto.UserDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Sql({"/schema.sql", "/data.sql"})
@TestPropertySource("classpath:test.properties")
@Transactional
@SpringBootTest
class QnaControllerIntegrationTest {

    @Autowired
    private QnaController qnaController;

    private MockMvc mock;

    private MockHttpSession session;

    @BeforeEach
    public void setUp() {
        mock = MockMvcBuilders.standaloneSetup(qnaController)
                .setCustomArgumentResolvers(new LoginUserMethodArgumentResolver(), new PageableHandlerMethodArgumentResolver())
                .build();

        UserDto.UserSessionDto userSessionDto = new UserDto.UserSessionDto("lucas", "test");

        session = new MockHttpSession();
        session.setAttribute("sessionedUser", userSessionDto);
    }

    @DisplayName("makeQnaHtml 테스트 - 요청시 Http Status 2XX 반환")
    @Test
    void makeQnaHtml_Nothing_HttpStatus2XX() throws Exception {
        // given

        // when // then
        mock.perform(get("/questions"))
                .andExpect(status().isOk());
    }

    @DisplayName("makeQna 테스트 - writer, title, contents 입력시 Http Status 3XX 반환")
    @Test
    void makeQna_WriterAndTitleAndContents_HttpStatus3XX() throws Exception {
        // given

        // when // then
        mock.perform(post("/questions")
                        .session(session)
                        .param("title", "test title")
                        .param("contents", "test contents"))
                .andExpect(status().is3xxRedirection())
                .andDo(print());
    }

    @DisplayName("findQnaList 테스트 - page가 0일 때, attribute에 qnaList 가 존재")
    @Test
    void findQnaList_Page0_HasAttributeQnaList() throws Exception {
        // given

        // when // then
        mock.perform(get("/")
                        .param("page", "0"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("qnaList"))
                .andDo(print());
    }

    @DisplayName("findQna 테스트 - 올바른 qnaId가 주어질때, attribute에 qna 반환")
    @Test
    void findQna_CorrectQnaId_attributeExistsQna() throws Exception {
        // given
        Integer qnaId = 1;

        // when // then
        mock.perform(get("/questions/{qnaId}", qnaId))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("qna"))
                .andDo(print());
    }

    @DisplayName("findQna 테스트 - 잘못된 qnaId 입력시 QnaNotFoundException Throw")
    @Test
    void findQna_IncorrectQnaId_ThrowQnaNotFoundException() throws QnaNotFoundException {
        // given
        Integer qnaId = 3;

        // when // then
        assertThatThrownBy(() ->
                mock.perform(get("/questions/{qnaId}", qnaId))
        ).hasCause(new QnaNotFoundException(qnaId));
    }
}
