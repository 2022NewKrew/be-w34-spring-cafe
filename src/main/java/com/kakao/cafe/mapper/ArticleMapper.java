package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.article.ArticleDetailResponseDto;
import com.kakao.cafe.dto.article.ArticleListResponseDto;
import com.kakao.cafe.dto.article.ArticleRegisterRequestDto;
import com.kakao.cafe.dto.article.ArticleUpdateFormResponseDto;
import com.kakao.cafe.dto.article.ArticleUpdateRequestDto;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public List<ArticleListResponseDto> articleListToArticleListResponseDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(this::articleToArticleListResponseDto)
                .collect(Collectors.toList());
    }

    private ArticleListResponseDto articleToArticleListResponseDto(Article article) {
        String articleId = article.getArticleId().toString();
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
        String articleId = article.getArticleId().toString();
        String title = article.getTitle().getValue();
        String content = article.getContent().getValue();
        String writer = article.getWriter().getUserName().getValue();
        String createdAt = article.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        int viewCount = article.getViewCount().getValue();

        return new ArticleDetailResponseDto(articleId, title, content, writer, createdAt, viewCount);
    }

    public ArticleUpdateFormResponseDto articleToArticleUpdateFormResponseDto(Article article) {
        String articleId = article.getArticleId().toString();
        String title = article.getTitle().getValue();
        String content = article.getContent().getValue();

        return new ArticleUpdateFormResponseDto(articleId, title, content);
    }

    public Article articleUpdateRequestDtoToArticle(UUID articleId, ArticleUpdateRequestDto dto, User user) {
        Title title = new Title(dto.getTitle());
        Content content = new Content(dto.getContent());

        return new Article(articleId, title, content, user);
    }
}
