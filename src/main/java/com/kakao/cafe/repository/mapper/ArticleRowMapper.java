package com.kakao.cafe.repository.mapper;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.ArticleDeleted;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;
import com.kakao.cafe.domain.article.ViewCount;
import com.kakao.cafe.domain.user.Email;
import com.kakao.cafe.domain.user.Name;
import com.kakao.cafe.domain.user.Password;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.domain.user.UserName;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class ArticleRowMapper implements RowMapper<Article> {

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        UUID id = rs.getObject("articles_id", UUID.class);
        Title title = new Title(rs.getString("title"));
        Content content = new Content(rs.getString("content"));
        LocalDateTime createdAt = rs.getObject("created_at", LocalDateTime.class);
        ViewCount viewCount = new ViewCount(rs.getInt("view_count"));
        ArticleDeleted articleDeleted = ArticleDeleted.from(rs.getBoolean("deleted"));
        UUID users_id = rs.getObject("users_id", UUID.class);
        UserName userName = new UserName(rs.getString("username"));
        Password password = new Password(rs.getString("password"));
        Name name = new Name(rs.getString("name"));
        Email email = new Email(rs.getString("email"));
        User writer = new User(users_id, userName, password, name, email);
        return new Article(id, title, content, writer, createdAt, viewCount, articleDeleted);
    }
}
