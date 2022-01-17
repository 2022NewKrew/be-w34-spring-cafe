package com.kakao.cafe.domain.article.repository;

import com.kakao.cafe.domain.article.dto.ArticleRowDataDto;
import com.kakao.cafe.domain.user.repository.UserRepository;
import com.kakao.cafe.global.sql.TableName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    @Autowired
    public JdbcTemplateArticleRepository(DataSource dataSource, UserRepository userRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRepository = userRepository;
    }

    @Override
    public ArticleRowDataDto save(ArticleRowDataDto articleRowDataDto) {
//        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
//        jdbcInsert.withTableName("articles").usingGeneratedKeyColumns("id");
//
//        Map<String, Object> parameters = new HashMap<>();
//
//        parameters.put("writer", articleRowDataDto.getWriterId());
//        parameters.put("title", articleRowDataDto.getTitle());
//        parameters.put("contents", articleRowDataDto.getContents());
//        parameters.put("registerDateTime", articleRowDataDto.getRegisterDateTime());
//
//        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
//        articleRowDataDto.setId(key.longValue());

        final String sql = "insert into " + TableName.ARTICLE + " (`writer`, `title`, `contents`, `registerDateTime`) values (?,?,?)";

        jdbcTemplate.update(sql, articleRowDataDto.getWriterId(), articleRowDataDto.getTitle(), articleRowDataDto.getContents(), articleRowDataDto.getRegisterDateTime());

        return articleRowDataDto;
    }

    @Override
    public Optional<ArticleRowDataDto> findById(Long id) {
        List<ArticleRowDataDto> result = jdbcTemplate.query("select * from " + TableName.ARTICLE +" where id=?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<ArticleRowDataDto> findByWriter(String writer) {
        List<ArticleRowDataDto> result = jdbcTemplate.query("select * from " + TableName.ARTICLE +" where writer=?", articleRowMapper(), writer);
        return result.stream().findAny();
    }

    private RowMapper<ArticleRowDataDto> articleRowMapper() {
        return (rs, rowNum) -> {
            ArticleRowDataDto articleRowDataDto = new ArticleRowDataDto();
            articleRowDataDto.setId(rs.getLong("id"));
            articleRowDataDto.setWriterId(rs.getLong("writer"));
            articleRowDataDto.setTitle(rs.getString("title"));
            articleRowDataDto.setContents(rs.getString("contents"));
            articleRowDataDto.setRegisterDateTime(rs.getTimestamp("registerDateTime").toLocalDateTime());
            return articleRowDataDto;
        };
    }

    @Override
    public List<ArticleRowDataDto> findAll() {
        return jdbcTemplate.query("select * from " + TableName.ARTICLE, articleRowMapper());
    }
}
