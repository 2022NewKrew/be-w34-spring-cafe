package com.kakao.cafe.article.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.ArticleRepository;
import com.kakao.cafe.article.service.dto.AllArticlesListServiceResponse;
import com.kakao.cafe.article.service.dto.ArticleReadServiceResponse;
import com.kakao.cafe.article.service.dto.CreateArticleServiceRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class ArticleService {

    private final ArticleRepository articleRepository;

    public void createArticle(CreateArticleServiceRequest req) {
        articleRepository.persist(makeArticle(req));
    }

    public AllArticlesListServiceResponse getAllArticleViewDTO(Long startIndex) {
        List<Article> allArticles = articleRepository.findAll();
        Collections.reverse(allArticles);

        return new AllArticlesListServiceResponse(allArticles);
    }

    public ArticleReadServiceResponse getArticleReadViewDTO(Long id) {
        articleRepository.increaseHit(id);
        Article article = articleRepository.find(id)
                                           .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글 입니다."));

        return ArticleServiceDTOMapper.convertToArticleReadServiceResponse(article);
    }


    public void validateAuthor(Long articleId, Long authorId) {
        Optional<Article> article = articleRepository.find(articleId);
        Long id = article.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다.")).getAuthorId();
        if (id != authorId) {
            throw new IllegalArgumentException("작성자 본인에게만 수정/삭제 권한이 있습니다.");
        }
    }

    public void updateArticle(long articleId, String title, String contents) {
        Optional<Article> findArticle = articleRepository.find(articleId);
        Article article = findArticle.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
        articleRepository.updateArticle(makeUpdatingArticle(article, title, contents));
    }

    private Article makeArticle(CreateArticleServiceRequest req) {
        return Article.builder()
                      .title(req.getTitle())
                      .authorId(req.getAuthorId())
                      .authorStringId(req.getAuthorStringId())
                      .contents(req.getContents())
                      .hits(0)
                      .build();
    }

    private Article makeUpdatingArticle(Article article, String title, String contents) {
        return Article.builder()
                      .title(title)
                      .id(article.getId())
                      .authorId(article.getAuthorId())
                      .contents(contents)
                      .hits(article.getHits())
                      .date(article.getDate())
                      .build();
    }

    public void deleteArticle(Long parseLong) {
        articleRepository.deleteArticle(parseLong);
    }
}
