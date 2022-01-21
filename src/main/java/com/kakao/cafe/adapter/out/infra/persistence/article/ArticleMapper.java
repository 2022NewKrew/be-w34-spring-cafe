package com.kakao.cafe.adapter.out.infra.persistence.article;

import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_CONTENTS;
import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_CREATED_AT;
import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_TITLE;
import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_USER_ID;
import static com.kakao.cafe.adapter.out.infra.persistence.article.JdbcArticleRepository.COLUMN_WRITER;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.exceptions.IllegalDateException;
import com.kakao.cafe.domain.article.exceptions.IllegalTitleException;
import com.kakao.cafe.domain.article.exceptions.IllegalWriterException;
import com.kakao.cafe.domain.user.exceptions.IllegalUserIdException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ArticleMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        try {
            Article article = new Article.Builder().userId(rs.getString(COLUMN_USER_ID))
                                                   .writer(rs.getString(COLUMN_WRITER))
                                                   .title(rs.getString(COLUMN_TITLE))
                                                   .contents(rs.getString(COLUMN_CONTENTS))
                                                   .createdAt(rs.getString(COLUMN_CREATED_AT))
                                                   .deleted(false)
                                                   .build();
            article.setId(rs.getInt(COLUMN_ID));
            return article;
        } catch (IllegalWriterException e) {
            throw new SQLException("DB에 저장된 writer가 잘못되었습니다.");
        } catch (IllegalTitleException e) {
            throw new SQLException("DB에 저장된 title이 잘못되었습니다.");
        } catch (IllegalDateException e) {
            throw new SQLException("DB에 저장된 createdAt 값이 잘못되었습니다.");
        } catch (IllegalUserIdException e) {
            throw new SQLException("DB에 저장된 userId 값이 잘못되었습니다.");
        }
    }
}
