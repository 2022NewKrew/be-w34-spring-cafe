package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public JdbcTemplatePostRepository(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("postid");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("writer", post.getWriter());
        parameters.put("title", post.getTitle());
        parameters.put("createddate", post.getCreateddate());


        Number key = jdbcInsert.executeAndReturnKey(new
                MapSqlParameterSource(parameters));
        post.setPostId(key.intValue());
        return post;
    }

    @Override
    public Optional<Post> findById(int id) {
        List<Post> result = jdbcTemplate.query("select * from post where postid = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Post> findByTitle(String title) {
        List<Post> result = jdbcTemplate.query("select * from post where title = ?", memberRowMapper(), title);
        return result.stream().findAny();
    }

    @Override
    public Optional<Post> findByWriter(String writer) {
        List<Post> result = jdbcTemplate.query("select * from post where title = ?", memberRowMapper(), writer);
        return result.stream().findAny();
    }

    @Override
    public List<Post> findAll() {
        return jdbcTemplate.query("select * from post", memberRowMapper());
    }

    private RowMapper<Post> memberRowMapper(){
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setPostId(rs.getInt("postid"));
            post.setTitle(rs.getString("title"));
            post.setWriter(rs.getString("writer"));
            post.setCreateddate(rs.getDate("createddate").toLocalDate());
            return post;
        };
    }
}
