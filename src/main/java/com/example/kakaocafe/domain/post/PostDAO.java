package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import com.example.kakaocafe.domain.post.dto.PostOfTableRow;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostDAO {
    private final JdbcTemplate jdbcTemplate;

    public void create(WritePostForm writePostForm) {
        final String sql = "INSERT INTO `post` (`user_id`, `title`, `contents`) VALUES(?,?,?)";

        final Object[] params = {
                writePostForm.getWriterId(),
                writePostForm.getTitle(),
                writePostForm.getContents()
        };

        jdbcTemplate.update(sql, params);
    }

    public List<PostOfTableRow> getAllPostOfTableRow() {
        final String sql = "SELECT p.id, p.title, p.contents, FORMATDATETIME(p.created, 'yyyy-MM-dd') as `created`, u.name as writer " +
                "FROM `post` as p " +
                "JOIN `user` as u on p.user_id=u.id";

        return jdbcTemplate.query(sql, postOfTableRowMapper());
    }

    public Optional<Post> getPostById(long id) {
        final String sql = "SELECT p.id, p.title, p.contents, FORMATDATETIME(p.created, 'yyyy-MM-dd') as `created`, u.name as writer " +
                "FROM `post` as p " +
                "JOIN `user` as u on p.user_id=u.id " +
                "WHERE `id` = ?";

        final List<Post> post = jdbcTemplate.query(sql, postMapper(), id);
        return Optional.ofNullable(DataAccessUtils.singleResult(post));
    }

    private RowMapper<Post> postMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String writer = rs.getString("writer");
            final String title = rs.getString("title");
            final String contents = rs.getString("contents");
            final String created = rs.getString("created");

            return new Post(id, writer, title, contents, created);
        };
    }

    private RowMapper<PostOfTableRow> postOfTableRowMapper() {
        return (rs, rowNum) -> {
            final long id = rs.getLong("id");
            final String writer = rs.getString("writer");
            final String title = rs.getString("title");
            final String created = rs.getString("created");

            return new PostOfTableRow(id, writer, title, created);
        };
    }
}
