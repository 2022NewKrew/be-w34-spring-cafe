package com.kakao.cafe.qna.service;

import com.kakao.cafe.qna.DTO.ArticleSummaryDTO;
import com.kakao.cafe.qna.DTO.QuestionDTO;
import com.kakao.cafe.qna.domain.Article;
import com.kakao.cafe.qna.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ArticleRepository articleRepository;

    public void submitArticle(QuestionDTO packedArgs) {
        articleRepository.submitArticle(packedArgs.getWriter(), packedArgs.getTitle(), packedArgs.getContent());
    }

    public List<ArticleSummaryDTO> getArticleSummaryLst() {
        List<Article> articleLst = articleRepository.getArticleLst();
        return articleLst.stream()
                .map(this::makeArticleSummaryDTOFromArticle)
                .collect(Collectors.toList());

    }

    private ArticleSummaryDTO makeArticleSummaryDTOFromArticle(Article article) {
        return new ArticleSummaryDTO(article.getTitle(), article.getWriter(), article.getWrittenTime());
    }
}
