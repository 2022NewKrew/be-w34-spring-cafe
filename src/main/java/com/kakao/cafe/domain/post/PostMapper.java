package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toEntity(PostDto postDto);
}
