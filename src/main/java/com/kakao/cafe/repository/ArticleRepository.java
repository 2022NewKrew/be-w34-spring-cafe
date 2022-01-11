package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {

    List<Article> findAll();
    Article findById(int id);
    int save(Article article);

}
