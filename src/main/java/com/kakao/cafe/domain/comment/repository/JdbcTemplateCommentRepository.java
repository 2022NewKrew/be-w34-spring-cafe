package com.kakao.cafe.domain.comment.repository;

import com.kakao.cafe.domain.comment.dto.CommentRawDataDto;
import com.kakao.cafe.global.sql.TableName;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateCommentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcTemplateCommentRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public CommentRawDataDto save(CommentRawDataDto commentRawDataDto) {
        final String sql = "insert into " + TableName.COMMENT + " (writerId, articleId, contents) values(?,?,?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(sql, new String[]{"id"});
            ps.setLong(1, commentRawDataDto.getWriterLongId());
            ps.setLong(2, commentRawDataDto.getArticleId());
            ps.setString(3, commentRawDataDto.getContents());
            return ps;
        }, keyHolder);

        commentRawDataDto.setId(keyHolder.getKey().longValue());
        return commentRawDataDto;
    }

    public Optional<CommentRawDataDto> findById(Long commentId) {
        final String sql = "select * from " + TableName.COMMENT + " where id=? and isDeleted=false";

        List<CommentRawDataDto> result = jdbcTemplate.query(sql, commentRowMapper(), commentId);
        return result.stream().findAny();
    }

    private RowMapper<CommentRawDataDto> commentRowMapper() {
        return (rs, rowNum) -> {
               CommentRawDataDto dto = new CommentRawDataDto();
               dto.setId(rs.getLong("id"));
               dto.setWriterLongId(rs.getLong("writerID"));
               dto.setArticleId(rs.getLong("articleId"));
               dto.setContents(rs.getString("contents"));
               dto.setRegisterDateTime(rs.getTimestamp("registerDateTime").toLocalDateTime());

               return dto;
        };
    }

    public boolean deleteById(Long commentId) {
        final String sql = "update " + TableName.COMMENT + " set isDeleted=true where id=?";

        if (jdbcTemplate.update(sql, commentId) <= 0) throw new RuntimeException("삭제에 실패하였습니다.");
        return true;
    }
}
