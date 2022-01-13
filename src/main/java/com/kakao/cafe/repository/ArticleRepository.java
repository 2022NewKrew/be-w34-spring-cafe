package com.kakao.cafe.repository;

import com.kakao.cafe.domain.dto.ArticleCreateCommand;
import com.kakao.cafe.domain.entity.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface ArticleRepository {
    void store(ArticleCreateCommand acc);
    Article retrieve(Long id);
    void modify(Long id, Article article);
    void delete(Long id);
    List<Article> toList();
}
