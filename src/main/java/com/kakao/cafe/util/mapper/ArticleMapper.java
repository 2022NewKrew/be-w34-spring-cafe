package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.*;
import com.kakao.cafe.dto.ArticleDto;
import com.kakao.cafe.dto.ArticleFormDto;
import com.kakao.cafe.dto.ArticlePreviewDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleMapper {

    public static Article toArticle(ArticleFormDto articleFormDto) {
        return new Article(
                new Title(articleFormDto.getTitle()),
                new Contents(articleFormDto.getContents())
        );
    }

    public static Article toArticle(Long articleId, ArticleFormDto articleFormDto) {
        return new Article(
                new Id(articleId),
                new Title(articleFormDto.getTitle()),
                new Contents(articleFormDto.getContents())
        );
    }

    public static ArticleDto toArticleDto(Article article) {
        return new ArticleDto(
                article.getArticleId().getId(),
                article.getWriterName().getName(),
                article.getWriteTime().getWriteTime(),
                article.getTitle().getTitle(),
                article.getContents().getContents(),
                article.getComments().getComments().size(),
                article.getComments().getComments()
                        .stream()
                        .map(CommentMapper::toCommentDto)
                        .collect(Collectors.toList())
        );
    }

    public static List<ArticlePreviewDto> toArticlePreviewDto(Articles articles) {
        return articles.getArticles()
                .stream()
                .map(article -> new ArticlePreviewDto(
                        article.getArticleId().getId(),
                        article.getWriterName().getName(),
                        article.getWriteTime().getWriteTime(),
                        article.getTitle().getTitle()))
                .collect(Collectors.toList());
    }
}
