package com.kakao.cafe.rowmapper;

import com.kakao.cafe.constant.OffsetId;
import com.kakao.cafe.domain.Post;
import com.kakao.cafe.dto.post.PostViewDto;
import com.kakao.cafe.dto.post.SimplePostInfo;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class PostRowMapper {

    public RowMapper<Post> getPostRowMapper() {
        return (resultSet, rowNum) ->
                Post.builder()
                        .id(resultSet.getLong("id"))
                        .title(resultSet.getString("title"))
                        .contents(resultSet.getString("contents"))
                        .viewNum(resultSet.getInt("view_num"))
                        .createdAt(resultSet.getTimestamp("created_at").toInstant().atOffset(ZoneOffset.of(OffsetId.KR_ID)))
                        .userId(resultSet.getLong("user_id"))
                        .build();
    }

    public RowMapper<PostViewDto> getPostViewDtoRowMapper() {
        return (resultSet, rowNum) -> {
            LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
            String createdDateTime = String.format("%d-%d-%d %d:%d:%d", createdAt.getYear(), createdAt.getMonth().getValue(), createdAt.getDayOfMonth(), createdAt.getHour(), createdAt.getMinute(), createdAt.getSecond());
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
            LocalDateTime createdAt = resultSet.getTimestamp("created_at").toLocalDateTime();
            String createdDate = String.format("%d-%d-%d", createdAt.getYear(), createdAt.getMonth().getValue(), createdAt.getDayOfMonth());
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
