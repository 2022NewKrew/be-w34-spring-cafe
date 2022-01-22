package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.reply.Reply;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Data
public class ShowReplyDto {
    private Long id;
    private String comment;
    private String userId;
    private Long postId;
    private String regDateTime;

    public ShowReplyDto(Reply reply){
        id = reply.getId();
        comment = reply.getComment();
        userId = reply.getUserId();
        postId = reply.getPostId();
        regDateTime = reply.getRegDateTime().format(DateTimeFormatter.ofPattern("[yyyy-MM-dd] HH:mm:ss"));
    }
}
