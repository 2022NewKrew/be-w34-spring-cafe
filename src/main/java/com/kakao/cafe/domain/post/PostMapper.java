package com.kakao.cafe.domain.post;

import com.kakao.cafe.interfaces.common.GenericMapper;
import com.kakao.cafe.interfaces.common.PostDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper extends GenericMapper<PostDto, Post> {
}
