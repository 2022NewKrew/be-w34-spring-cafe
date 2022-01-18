package com.kakao.cafe.article;

import com.kakao.cafe.users.User;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository {

    void save(Article article);

    default Optional<Article> findBySeq(long seq) { return Optional.empty(); }

    Optional<Article> findByTitle(String title);

    List<Article> findAll();

}
