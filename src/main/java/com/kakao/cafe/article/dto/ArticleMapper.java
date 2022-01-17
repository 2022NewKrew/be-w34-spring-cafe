package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import com.kakao.cafe.article.domain.Contents;
import com.kakao.cafe.article.domain.Title;
import com.kakao.cafe.user.domain.UserId;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ArticleMapper implements RowMapper<Article> {

    public Article toArticle(ArticleFormDto articleFormDto) {
        return new Article(
                new UserId(articleFormDto.getWriter()),
                new Date(),
                new Title(articleFormDto.getTitle()),
                new Contents(articleFormDto.getContents())
        );
    }

    public List<ArticleListDto> toListArticleDto(List<Article> articles) {
        return articles.stream()
                .map(article -> new ArticleListDto(
                        article.getWriterId().getUserId(),
                        article.getWriteTime(),
                        article.getTitle().getTitle())
                ).collect(Collectors.toList());
    }

    public ArticleDto toArticleDto(Article article) {
        return new ArticleDto(
                article.getWriterId().getUserId(),
                article.getWriteTime(),
                article.getTitle().getTitle(),
                article.getContents().getContents()
        );
    }

    public Article mapRow(ResultSet resultSet, int rowNumber) throws SQLException {
        return new Article(
                resultSet.getLong("ARTICLE_ID"),
                new UserId(resultSet.getString("WRITER_ID")),
                resultSet.getDate("WRITE_TIME"),
                new Title(resultSet.getString("TITLE")),
                new Contents(resultSet.getString("CONTENTS"))
        );
    }
}
