package com.kakao.cafe.qna.service;

import com.kakao.cafe.qna.DTO.QuestionDTO;
import com.kakao.cafe.qna.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ArticleRepository articleRepository;

    public void submitArticle(QuestionDTO packedArgs) {
        articleRepository.submitArticle(packedArgs.getWriter(), packedArgs.getTitle(), packedArgs.getContent());
    }
}
