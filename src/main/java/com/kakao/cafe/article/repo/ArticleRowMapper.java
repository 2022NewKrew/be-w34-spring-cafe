package com.kakao.cafe.article.repo;

import com.kakao.cafe.article.model.Article;
import com.kakao.cafe.common.BaseEntityRowMapper;
import com.kakao.cafe.user.repo.UserRepository;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRowMapper implements BaseEntityRowMapper<Article> {
    private final UserRepository userRepository;

    public ArticleRowMapper(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
        Article article = new Article();
        article.setId(rs.getLong("ID"));
        article.setTitle(rs.getString("TITLE"));
        article.setContent(rs.getString("CONTENT"));
        article.setAuthor(userRepository.fetch(rs.getLong("AUTHOR_ID")));
        mapBaseProperties(article, rs);
        return article;
    }
}
