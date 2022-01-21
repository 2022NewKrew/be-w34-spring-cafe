package com.kakao.cafe.domain.reply;

import com.kakao.cafe.interfaces.common.ReplyDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReplyMapper {
    Reply toEntity(ReplyDto replyDto);
}
