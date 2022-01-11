package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleListResponseDto articleToArticleListResponseDto(Article article) {
        int articleId = article.getArticleId().getValue();
        String title = article.getTitle().getValue();
        String writer = article.getWriter().getName().getValue();
        String createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int viewCount = article.getViewCount().getValue();

        return new ArticleListResponseDto(articleId, title, writer, createdAt, viewCount);
    }

    public Article articleRegisterRequestDtoToArticle(ArticleRegisterRequestDto dto, User user) {
        Title title = new Title(dto.getTitle());
        Content content = new Content(dto.getContent());
        return new Article(title, content, user);
    }

    public ArticleDetailResponseDto articleToArticleDetailResponseDto(Article article) {
        String title = article.getTitle().getValue();
        String content = article.getContent().getValue();
        String writer = article.getWriter().getName().getValue();
        String createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int viewCount = article.getViewCount().getValue();

        return new ArticleDetailResponseDto(title, content, writer, createdAt, viewCount);
    }
}
