package com.kakao.cafe.rowmapper;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

@Component
public class PostRowMapper {

    public RowMapper<Post> getPostRowMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            return Post.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .contents(resultSet.getString("contents"))
                    .viewNum(resultSet.getInt("view_num"))
                    .createdAt(createdAt)
                    .userId(resultSet.getLong("user_id"))
                    .build();
        };
    }

    public RowMapper<PostViewDto> getPostViewDtoRowMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            String createdDateTime = createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            return PostViewDto.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .contents(resultSet.getString("contents"))
                    .writerNickName(resultSet.getString("u_nick_name"))
                    .createdDateTime(createdDateTime)
                    .viewNum(resultSet.getInt("view_num"))
                    .build();
        };
    }

    public RowMapper<SimplePostInfo> getSimplePostInfoRowMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAtLocal = resultSet.getTimestamp("created_at").toLocalDateTime();
            OffsetDateTime createdAt = createdAtLocal.atOffset(ZoneOffset.of(ZoneOffset.systemDefault().getId()));
            String createdDate = createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE);
            return SimplePostInfo.builder()
                    .id(resultSet.getLong("id"))
                    .title(resultSet.getString("title"))
                    .writerNickName(resultSet.getString("u_nick_name"))
                    .createdDate(createdDate)
                    .viewNum(resultSet.getInt("view_num"))
                    .build();
        };
    }
}
