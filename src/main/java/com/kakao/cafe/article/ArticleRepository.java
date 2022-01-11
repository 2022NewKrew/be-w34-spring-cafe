package com.kakao.cafe.article;

import com.kakao.cafe.users.User;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void update(Article article);

    Optional<Article> findByTitle(String title);

    List<Article> getAllArticles();

}
