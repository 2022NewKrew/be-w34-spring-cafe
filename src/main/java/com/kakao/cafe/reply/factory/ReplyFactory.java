package com.kakao.cafe.reply.factory;

import com.kakao.cafe.reply.domain.Reply;
import com.kakao.cafe.reply.dto.ReplyViewDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReplyFactory {

    private ReplyFactory() {
    }

    public static Reply toComment(Long articlePK, Long userPK, String writer, String contents) {
        return new Reply(articlePK, userPK, writer, contents);
    }

    public static List<ReplyViewDTO> toDTO(List<Reply> replyList) {
        return replyList.stream()
                .map(ReplyViewDTO::new)
                .collect(Collectors.toList());
    }
}
