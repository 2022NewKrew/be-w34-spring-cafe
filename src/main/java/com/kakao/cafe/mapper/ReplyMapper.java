package com.kakao.cafe.mapper;

import com.kakao.cafe.dto.ReplyDto;
import com.kakao.cafe.entity.ReplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    @Mapping(target = "writeDate", expression = "java(java.time.LocalDateTime.now())")
    ReplyEntity toReplyEntity(ReplyDto replyDto);

    @Mapping(source = "writeDate", target = "writeDate", dateFormat = "yyyy-MM-dd hh-mm-ss")
    ReplyDto toReplyDto(ReplyEntity replyEntity);

    @Mapping(source = "writeDate", target = "writeDate", dateFormat = "yyyy-MM-dd hh-mm-ss")
    List<ReplyDto> toReplyDtoList(List<ReplyEntity> replyEntityList);
}
