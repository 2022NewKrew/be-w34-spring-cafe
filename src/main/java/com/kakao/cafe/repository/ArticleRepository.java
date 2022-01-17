package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.article.ArticleContents;
import com.kakao.cafe.domain.dto.article.ArticleCreateCommand;
import com.kakao.cafe.domain.dto.article.ArticleListShow;
import com.kakao.cafe.domain.entity.Article;

import java.util.List;

public interface ArticleRepository {
    void store(ArticleCreateCommand acc);
    ArticleContents retrieve(Long id);
    void modify(Long id, Article article);
    void delete(Long id);
    List<ArticleListShow> toList();
}
