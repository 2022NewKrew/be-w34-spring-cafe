package com.kakao.cafe.qna.service;

import com.kakao.cafe.qna.DTO.*;
import com.kakao.cafe.qna.domain.Article;
import com.kakao.cafe.qna.domain.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ArticleRepository articleRepository;

    public void submitArticle(QuestionDTO packedArgs) {
        articleRepository.submitArticle(packedArgs.getWriter(), packedArgs.getTitle(), packedArgs.getContents());
    }

    public List<ArticleSummaryDTO> getArticleSummaryLst() {
        List<Article> articleLst = articleRepository.getArticleLst();
        return articleLst.stream()
                .map(this::makeArticleSummaryDTOFromArticle)
                .collect(Collectors.toList());

    }

    private ArticleSummaryDTO makeArticleSummaryDTOFromArticle(Article article) {
        return new ArticleSummaryDTO(article.getTitle(), article.getWriter(), article.getWrittenTime(),
                article.getPoint(), String.valueOf(article.getId()));
    }

    public PackedArticleDTO getPackedArticle(String idx) {
        ArticleDTO article = makeWrittenThingDTOFromArticle(articleRepository.getArticleLst().get(Integer.parseInt(idx) - 1));
        List<ReplyDTO> replies = new ArrayList<>();

        return makePackedArticle(article, replies);
    }

    private ArticleDTO makeWrittenThingDTOFromArticle(Article article) {
        //TODO addr 변경
        return new ArticleDTO(article.getWriter(), "https://graph.facebook.com/v2.3/1324855987/picture",
                article.getWrittenTime(), article.getContent(), article.getTitle(), String.valueOf(article.getId()));
    }

    private PackedArticleDTO makePackedArticle(ArticleDTO article, List<ReplyDTO> replies) {
        return new PackedArticleDTO(article, replies, replies.size());
    }


}
