package com.kakao.cafe.dao;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PostDao {

    private final static Logger LOGGER = LoggerFactory.getLogger(PostDao.class);
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int insert(Post post) {
        String queryString = "insert into POSTS (title, writer, contents) values(?, ?, ?);";
        jdbcTemplate.update(queryString, post.getTitle(), post.getWriter(), post.getContents());
        return jdbcTemplate.queryForObject("select max(id) from POSTS;", Integer.class);
    }

    public Post findById(long id) {
        String queryString = "select * from POSTS where id = ?;";
        Map<String, Object> res = jdbcTemplate.queryForMap(queryString, id);
        return mapToPost(res);
    }

    public Posts findAll() {
        String queryString = "select * from POSTS WHERE DELETED = FALSE";
        List<Map<String, Object>> res = jdbcTemplate.queryForList(queryString);
        return new Posts(res.stream().map(this::mapToPost).collect(Collectors.toList()));
    }

    public void deleteAll() {
        String queryString = "delete from POSTS;";
        jdbcTemplate.update(queryString);
    }

    public int update(Post post) {
        String queryString = "update POSTS set title = ?, contents = ? where writer = ?;";
        return jdbcTemplate.update(queryString, post.getTitle(), post.getContents(), post.getWriter());
    }

    public int delete(long id) {
        String queryString = "UPDATE POSTS SET DELETED = TRUE where id = ?";
        return jdbcTemplate.update(queryString, id);
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
