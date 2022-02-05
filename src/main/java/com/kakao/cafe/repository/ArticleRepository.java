package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.Articles;
import com.kakao.cafe.domain.Id;
import com.kakao.cafe.domain.UserId;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository {
    boolean save(Article article, UserId userId);

    Article findByArticleId(Id articleId);

    Article findByArticleIdAndLoginId(Id articleId, UserId loginId);

    Articles findAll();

    boolean update(Article article, UserId userId);

    boolean delete(Id articleId, UserId loginId);
}
