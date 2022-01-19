package com.kakao.cafe.reply.mapper;

import com.kakao.cafe.reply.dto.request.ReplyCreateRequest;
import com.kakao.cafe.reply.dto.response.ReplyDetailResponse;
import com.kakao.cafe.reply.entity.Reply;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ReplyMapper {

    ReplyMapper INSTANCE = Mappers.getMapper(ReplyMapper.class);

    Reply replyCreateRequestToEntity(ReplyCreateRequest replyCreateRequest);
    ReplyDetailResponse replyToReplyDetailResponse(Reply reply);
}
