package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper(componentModel = "spring")
public interface PostMapper extends RowMapper<Post> {
    @Mapping(target = "id", ignore = true)
    Post toEntity(PostDto post);

    @Override
    default Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Post.builder()
                .id(rs.getLong("id"))
                .writer(rs.getString("writer"))
                .title(rs.getString("title"))
                .body(rs.getString("body"))
                .createdAt(rs.getTimestamp("created_at").toLocalDateTime())
                .isRemoved(rs.getBoolean("is_removed"))
                .build();
    }
}
