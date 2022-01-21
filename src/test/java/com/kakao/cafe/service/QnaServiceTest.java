package com.kakao.cafe.service;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.domain.Qna;
import com.kakao.cafe.dto.QnaDto;
import com.kakao.cafe.exception.QnaNotFoundException;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.repository.QnaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class QnaServiceTest {

    @Mock
    private QnaRepository jdbcQnaRepositoryImpl;

    @Mock
    private CommentRepository jdbcCommentRepositoryImpl;

    @InjectMocks
    private QnaService qnaService;

    @DisplayName("createQna 테스트, CreateQnaRequest가 주어 졌을 때, save가 1번 발생")
    @Test
    void createQna_CreateQnaRequest_verify1() {
        // given
        QnaDto.CreateQnaRequest createQnaRequest = new QnaDto.CreateQnaRequest("test", "test", "test contents");

        // when
        qnaService.createQna(createQnaRequest);

        // then
        verify(jdbcQnaRepositoryImpl, times(1)).save(any());
    }

    @DisplayName("findQnaList 테스트 - Qna가 DB에 2개 저장되어있을 경우 반환되는 QnsResponse List 크기가 2 ")
    @Test
    void findQnaList_TwoQna_ListOfQnaResponseSize2() {
        // given
        Qna qna1 = new Qna(1, "test writer1", "test title1", "test contents1", LocalDateTime.now());
        Qna qna2 = new Qna(2, "test writer2", "test title2", "test contents2", LocalDateTime.now());

        List<Qna> qnaList = new ArrayList<>();
        qnaList.add(qna1);
        qnaList.add(qna2);

        when(jdbcQnaRepositoryImpl.findAllByDeleted(false)).thenReturn(qnaList);

        // when
        List<QnaDto.QnaResponse> result = qnaService.findQnaList();

        // then
        Assertions.assertEquals(2, result.size());

    }

    @DisplayName("findQna 테스트 - Qna가 존재할 때 QnaResponse가 Not null 이다")
    @Test
    void findQna_ExistQna_QnaResponseNotNull() {
        // given
        Qna qna = new Qna(1, "test writer", "test title", "test contents", LocalDateTime.now());
        when(jdbcQnaRepositoryImpl.findById(1)).thenReturn(Optional.of(qna));

        List<Comment> comments = new ArrayList<>();
        Comment comment = new Comment(1, "lucas", "test", 1, LocalDateTime.now());
        comments.add(comment);

        when(jdbcCommentRepositoryImpl.findByQnaIdAndDeleted(1, false)).thenReturn(comments);

        // when
        QnaDto.QnaResponse result = qnaService.findQna(1);

        // then
        Assertions.assertNotNull(result);
    }

    @DisplayName("findQna 테스트 - Qna가 존재하지 않을 때 QnaNotFoundException Throw")
    @Test
    void findQna_NotExistQna_ThrowQnaNotFoundException() {
        // given
        when(jdbcQnaRepositoryImpl.findById(1)).thenReturn(Optional.empty());

        // when // then
        Assertions.assertThrows(QnaNotFoundException.class, () -> {
            qnaService.findQna(1);
        });
    }
}
