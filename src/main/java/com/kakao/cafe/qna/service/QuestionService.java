package com.kakao.cafe.qna.service;

import com.kakao.cafe.qna.DTO.*;
import com.kakao.cafe.qna.domain.Article;
import com.kakao.cafe.qna.domain.ArticleRepository;
import com.kakao.cafe.user.domain.User;
import com.kakao.cafe.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

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

    public PackedArticleDTO getPackedArticle(int idx) {
        Article foundArticle = articleRepository.getArticleLst().get(idx - 1);
        ArticleDTO articleDTO = makeArticleDTOFromArticle(foundArticle,
                getWriterProfileAddress(foundArticle));

        //TODO : Reply 객체가 구현되면 로직 구현
        List<ReplyDTO> replies = new ArrayList<>();

        return makePackedArticle(articleDTO, replies);
    }

    private String getWriterProfileAddress(Article foundArticle) {
        //TODO : Optional
        User foundUser = userRepository.getUser(foundArticle.getWriter());
        if (foundUser == null) {
            return "https://graph.facebook.com/v2.3/1324855987/picture";
        }

        return foundUser.getPictureAddress();
    }

    private ArticleDTO makeArticleDTOFromArticle(Article article, String profileAddress) {
        return new ArticleDTO(article.getWriter(), profileAddress,
                article.getWrittenTime(), article.getContent(), article.getTitle(), article.getId());
    }

    private PackedArticleDTO makePackedArticle(ArticleDTO article, List<ReplyDTO> replies) {
        return new PackedArticleDTO(article, replies, replies.size());
    }
}
