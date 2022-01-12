package com.kakao.cafe.module.model.mapper;

import com.kakao.cafe.module.model.domain.Article;
import com.kakao.cafe.module.model.domain.User;

import java.time.LocalDateTime;

import static com.kakao.cafe.module.model.dto.ArticleDtos.*;

public class ArticleMapper {

    public static Article toArticle(Long id, Long author_id, String title, String contents,
                                    LocalDateTime created, Integer viewCount, Integer commentCount) {
        return new Article(id, author_id, title, contents, created, viewCount, commentCount);
    }

    public static ArticleListDto toArticleListDto(Article article, User author) {
        return new ArticleListDto(article.getId(), author.getName(), author.getId(),
                article.getTitle(), article.getCreated(), article.getCommentCount());
    }

    public static ArticleReadDto toArticleReadDto(Article article, User author) {
        return new ArticleReadDto(article.getId(), author.getName(), author.getId(),
                article.getTitle(), article.getCreated(), article.getContents(), article.getCommentCount());
    }
}
