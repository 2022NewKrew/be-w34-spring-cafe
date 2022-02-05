package com.kakao.cafe.util.mapper;

import com.kakao.cafe.domain.*;
import com.kakao.cafe.dto.ArticleDbDto;
import com.kakao.cafe.dto.ArticleSaveDto;
import com.kakao.cafe.dto.ArticleUpdateDto;
import com.kakao.cafe.dto.CommentDbDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArticleDbMapper {
    public static ArticleSaveDto toArticleSaveDto(Article article, UserId userId) {
        return new ArticleSaveDto(
                userId.getUserId(),
                article.getWriteTime().getWriteTime(),
                article.getTitle().getTitle(),
                article.getContents().getContents()
        );
    }

    public static ArticleUpdateDto toArticleUpdateDto(Article article, UserId userId) {
        return new ArticleUpdateDto(
                article.getTitle().getTitle(),
                article.getContents().getContents(),
                article.getArticleId().getId(),
                userId.getUserId()
        );
    }

    public static Article toArticle(ArticleDbDto articleDbDto) {
        return new Article(
                new Id(articleDbDto.getArticleId()),
                new Name(articleDbDto.getWriterName()),
                new WriteTime(articleDbDto.getWriteTime()),
                new Title(articleDbDto.getTitle()),
                new Contents(articleDbDto.getContents())
        );
    }

    public static Article toArticle(ArticleDbDto articleDbDto, List<CommentDbDto> commentDbDtos) {
        return new Article(
                new Id(articleDbDto.getArticleId()),
                new Name(articleDbDto.getWriterName()),
                new WriteTime(articleDbDto.getWriteTime()),
                new Title(articleDbDto.getTitle()),
                new Contents(articleDbDto.getContents()),
                new Comments(commentDbDtos.stream().map(commentDbDto -> new Comment(
                        new Id(commentDbDto.getCommentId()),
                        new Name(commentDbDto.getName()),
                        new WriteTime(commentDbDto.getTime()),
                        new Contents(commentDbDto.getContents()))
                ).collect(Collectors.toList()))
        );
    }

    public static Articles toArticles(List<ArticleDbDto> articleDbDtos) {
        return new Articles(articleDbDtos.stream()
                .map(ArticleDbMapper::toArticle)
                .collect(Collectors.toList()));
    }
}
