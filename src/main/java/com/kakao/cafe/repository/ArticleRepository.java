package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository {
    Article save(Article article);

}
