package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.Reply;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.ReplyRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper()
public interface ReplyMapper {
    ReplyMapper INSTANCE = Mappers.getMapper( ReplyMapper.class );

    default Reply toEntity (Long id, ReplyRequest replyRequest, User replyUser){
        return Reply.builder().contents(replyRequest.getContents()).articleId(id).userId(replyUser.getId()).build();
    }
}
