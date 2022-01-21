package com.kakao.cafe.mapper;

import com.kakao.cafe.domain.reply.Comment;
import com.kakao.cafe.domain.reply.Reply;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.dto.reply.ReplyListResponseDto;
import com.kakao.cafe.dto.reply.ReplyRegisterRequestDto;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class ReplyMapper {

    public Reply replyRegisterRequestDtoToReply(ReplyRegisterRequestDto dto, UUID articleId,
            User user) {
        Comment comment = new Comment(dto.getComment());

        return new Reply(comment, user, articleId);
    }

    public List<ReplyListResponseDto> replyListToReplyListResponseDtoList(List<Reply> replyList) {
        return replyList.stream()
                .map(this::replyToReplyListResponseDto)
                .collect(Collectors.toList());
    }

    private ReplyListResponseDto replyToReplyListResponseDto(Reply reply) {
        String replyId = reply.getReplyId().toString();
        String writer = reply.getWriter().getUserName().getValue();
        String comment = reply.getComment().getValue();
        String createdAt = reply.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        return new ReplyListResponseDto(replyId, writer, comment, createdAt);
    }
}
