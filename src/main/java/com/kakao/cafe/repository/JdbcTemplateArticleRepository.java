package com.kakao.cafe.repository;

import com.kakao.cafe.entiry.Article;
import com.kakao.cafe.entiry.User;
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
public class JdbcTemplateArticleRepository implements ArticleRepository{

    private final JdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

    @Autowired
    public JdbcTemplateArticleRepository(DataSource dataSource, UserRepository userRepository) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.userRepository = userRepository;
    }

    @Override
    public Article save(Article article) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("articles").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();

        // db에는 엔티티와 달리 writer(string)이 아닌 writer(user)의 id(Long)을 foreign key로 가지고 있어 변환
        Long writerId = findWriterId(article.getWriterUserId());

        parameters.put("writer", writerId);
        parameters.put("title", article.getTitle());
        parameters.put("contents", article.getContents());
        parameters.put("registerDateTime", article.getRegisterDateTime());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        article.setId(key.longValue());
        return article;
    }

    private Long findWriterId(String userId) {
        Optional<User> findUser = userRepository.findByUserId(userId);
        if (findUser.isEmpty()) throw new RuntimeException("해당 아이디를 가진 유저가 없습니다.");
        return findUser.get().getId();
    }

    @Override
    public Optional<Article> findById(Long id) {
        List<Article> result = jdbcTemplate.query("select * from articles where id=?", articleRowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<Article> articleRowMapper() {
        return (rs, rowNum) -> {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setWriterUserId(findByWriterId(rs.getLong("writer")));
            article.setTitle(rs.getString("title"));
            article.setContents(rs.getString("contents"));
            article.setRegisterDateTime(rs.getTimestamp("registerDateTime").toLocalDateTime());
            return article;
        };
    }

    public String findByWriterId(Long id){
        Optional<User> findUser = userRepository.findById(id);
        if (findUser.isEmpty()) throw new RuntimeException("해당 아이디를 가진 유저가 없습니다.");
        return findUser.get().getUserId();
    }

    @Override
    public Optional<Article> findByWriter(String writer) {
        Long writerId = findWriterId(writer);
        List<Article> result = jdbcTemplate.query("select * from articles where writer=?", articleRowMapper(), writer);
        return result.stream().findAny();
    }

    @Override
    public List<Article> findAll() {
        return jdbcTemplate.query("select * from articles", articleRowMapper());
    }
}
