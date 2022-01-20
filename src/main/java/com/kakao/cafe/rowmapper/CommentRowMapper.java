package com.kakao.cafe.rowmapper;

import com.kakao.cafe.domain.Comment;
import com.kakao.cafe.dto.comment.CommentViewDto;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class CommentRowMapper {

    public RowMapper<Comment> getCommentMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            return Comment.builder()
                    .id(resultSet.getLong("id"))
                    .contents(resultSet.getString("contents"))
                    .postId(resultSet.getLong("post_id"))
                    .userId(resultSet.getLong("user_id"))
                    .createdAt(createdAt)
                    .build();
        };
    }

    public RowMapper<CommentViewDto> getCommentViewDtoMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            return CommentViewDto.builder()
                    .contents(resultSet.getString("contents"))
                    .writerNickName(resultSet.getString("u_nick_name"))
                    .createdDateTime(createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE))
                    .build();
        };
    }
}
