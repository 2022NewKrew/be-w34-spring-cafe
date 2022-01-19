package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.PreviewArticleResponse;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ArticleRepository {
    Long save(Article article);

    List<PreviewArticleResponse> findAll();

    Optional<Article> findById(Long id);

    void updateArticle(Long id, Article updateArticle);

    void deleteArticle(Long id);
}
