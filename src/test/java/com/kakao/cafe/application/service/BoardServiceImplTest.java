package com.kakao.cafe.application.service;

import com.kakao.cafe.application.dto.PaginationDto;
import com.kakao.cafe.model.repository.ArticleRepository;
import com.kakao.cafe.model.repository.CommentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest {
    private final BoardService boardService;

    @Autowired
    public BoardServiceImplTest(@Qualifier("ArticleRepositoryJdbcImpl") ArticleRepository articleRepository,
                                @Qualifier("CommentRepositoryJdbcImpl") CommentRepository commentRepository,
                                ModelMapper modelMapper) {
        boardService = new BoardServiceImpl(articleRepository, commentRepository, modelMapper);
    }

    @Test
    @DisplayName("현재 페이지 및 보여주려는 게시글의 수에 맞게 페이지네이션 정보를 가져오는지 확인")
    void makePaginationInfo() {
        long currentPage = 13;
        int countPerPage = 15;

        PaginationDto paginationDto = boardService.makePaginationInfo(currentPage, countPerPage);

        assertThat(paginationDto.isStartRange()).isEqualTo(false);
        assertThat(paginationDto.isEndRange()).isEqualTo(false);
        assertThat(paginationDto.getStartPage()).isEqualTo(11);
        assertThat(paginationDto.getEndPage()).isEqualTo(15);
    }
}