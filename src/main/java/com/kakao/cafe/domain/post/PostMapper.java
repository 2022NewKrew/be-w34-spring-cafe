package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.jdbc.core.RowMapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends RowMapper {
    @Mapping(target = "id", ignore = true)
    Post toEntity(PostDto post);
}
