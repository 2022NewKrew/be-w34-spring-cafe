package com.kakao.cafe.vo;

import com.kakao.cafe.model.Reply;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVo {
    private boolean canDelete;
    private Reply reply;

    public ReplyVo(Reply reply, String loginUser) {
        this.reply = reply;
        this.canDelete = reply.getUserId().equals(loginUser);
    }
}
