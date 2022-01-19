package com.kakao.cafe.repository;

import com.kakao.cafe.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import com.kakao.cafe.dto.article.ArticleModifyCommand;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {
    void store(ArticleCreateCommand acc);
    Optional<Article> retrieve(Long id);
    void modify(Long id, ArticleModifyCommand amc);
    void delete(Long id);
    List<Article> toList();
}
