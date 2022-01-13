package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.article.Article;
import org.springframework.stereotype.Component;

@Component
public class InMemoryArticleMapper {
    public Article mapResult(Article result) {
        if(result == null) {
            return null;
        }
        return new Article(result.getArticleId(), result.getTitle(), result.getContent(), result.getWriter(), result.getCreatedAt(), result.getViewCount());
    }
}
