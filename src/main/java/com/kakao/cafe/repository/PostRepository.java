package com.kakao.cafe.repository;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostRepository {

    private final DataSource dataSource;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int insert(Post post) {
        String queryString = String.format("insert into Posts (title, writer, contents) " +
                "values('%s', '%s', '%s');", post.getTitle(), post.getWriter(), post.getContents());
        jdbcTemplate.execute(queryString);
        return jdbcTemplate.queryForObject("select max(id) from posts;", Integer.class);
    }

    public Post findById(long id) {
        String queryString = String.format("select * from posts where id = '%d'", id);
        Map<String, Object> res = jdbcTemplate.queryForMap(queryString);
        return mapToPost(res);
    }

    public Posts findAll() {
        String queryString = "select * from Posts";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(queryString);
        return new Posts(res.stream().map(this::mapToPost).collect(Collectors.toList()));
    }

    public void deleteAll() {
        String queryString = "delete from posts;";
        jdbcTemplate.execute(queryString);
    }


    private Post mapToPost(Map<String, Object> res) {
        return new Post.Builder()
                .title((String) res.get("TITLE"))
                .contents((String) res.get("CONTENTS"))
                .writer((String) res.get("WRITER"))
                .id((Long) res.get("ID"))
                .build();
    }


}
