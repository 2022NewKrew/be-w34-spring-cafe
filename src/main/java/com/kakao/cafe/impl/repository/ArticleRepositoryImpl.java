package com.kakao.cafe.impl.repository;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.repository.ArticleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository("articleRepository")
public class ArticleRepositoryImpl implements ArticleRepository {
    private final JdbcTemplate jdbcTemplate;

    public ArticleRepositoryImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public long insertArticle(ArticleDTO article) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement("insert into ARTICLETABLE (WRITERID, TITLE, CONTENTS, time) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, article.getWriterId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);

        return (long) keyHolder.getKey();
    }

    @Override
    public List<ArticleDTO> getAllArticle() {
        return jdbcTemplate.query("select A.id, WRITERID, USERID as writer, title, contents, views, to_char(A.time,'yyyy-MM-dd hh:mi') time from USERTABLE U join ARTICLETABLE A on U.ID = A.WRITERID",
                (rs, rowNum) -> new ArticleDTO(
                        rs.getLong("id"),
                        rs.getLong("writerId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getLong("views"),
                        rs.getString("time")
                )
        );

    }

    @Override
    public ArticleDTO getArticleById(long id) {
        return jdbcTemplate
                .queryForObject("select A.id, WRITERID, USERID as writer, title, contents,views,  to_char(A.time,'yyyy-MM-dd hh:mi') time from USERTABLE U join ARTICLETABLE A on U.ID = A.WRITERID where A.ID = ?",
                        (rs, rowNum) -> new ArticleDTO(
                                rs.getLong("id"),
                                rs.getLong("writerId"),
                                rs.getString("writer"),
                                rs.getString("title"),
                                rs.getString("contents"),
                                rs.getLong("views"),
                                rs.getString("time")
                        ), id);
    }

    @Override
    public int increaseViews(long articleId) {
        return jdbcTemplate.update("update ARTICLETABLE set views = views+1 where id = ?",
                articleId);
    }
}
