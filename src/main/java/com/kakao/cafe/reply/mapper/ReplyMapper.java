package com.kakao.cafe.reply.mapper;

import com.kakao.cafe.reply.dto.ReplyResDto;
import com.kakao.cafe.reply.entity.ReplyEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReplyMapper {

    public List<ReplyResDto> toReplyResDtoList(List<ReplyEntity> replyEntityList) {
        return replyEntityList.stream()
                .map(this::toReplyResDto)
                .collect(Collectors.toList());
    }

    public ReplyResDto toReplyResDto(ReplyEntity replyEntity) {
        return ReplyResDto.builder()
                .id(replyEntity.getId())
                .writer(replyEntity.getWriter())
                .comment(replyEntity.getComment())
                .createdAt(toDateString(replyEntity.getCreatedAt()))
                .build();
    }

    private String toDateString(LocalDateTime ldt) {
        return ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
