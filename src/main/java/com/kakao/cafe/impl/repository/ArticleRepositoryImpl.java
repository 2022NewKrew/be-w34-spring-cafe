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
import java.util.Objects;

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
            PreparedStatement ps = connection.prepareStatement("insert into  ArticleTable (WRITERID, TITLE, CONTENTS, time) values (?,?,?,now())", Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, article.getWriterId());
            ps.setString(2, article.getTitle());
            ps.setString(3, article.getContents());
            return ps;
        }, keyHolder);

        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public List<ArticleDTO> getAllArticle() {
        return jdbcTemplate.query("select A.id, WRITERID, USERID as writer, title, contents, views, date_format(A.time,'%Y-%m-%d %H:%i') time from UserTable U join ArticleTable A on U.ID = A.WRITERID",
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
                .queryForObject("select A.id, WRITERID, USERID as writer, title, contents,views,  date_format(A.time,'%Y-%m-%d %H:%i') time from UserTable U join ArticleTable A on U.ID = A.WRITERID where A.ID = ?",
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
        return jdbcTemplate.update("update ArticleTable set views = views+1 where id = ?",
                articleId);
    }

    @Override
    public int updateArticle(long id, ArticleDTO article) {
        return jdbcTemplate.update("update ArticleTable set title = ?, contents = ? where id = ?", article.getTitle(), article.getContents(), id);
    }

    @Override
    public int deleteArticle(long id) {
        return jdbcTemplate.update("delete from ArticleTable where id = ?", id);
    }
}
