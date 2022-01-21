package com.kakao.cafe.vo;

import com.kakao.cafe.helper.ReplyHelper;
import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.Reply;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostVo {
    private Post post;
    private List<ReplyVo> replies;
    private boolean canEdit;
    private int replyCnt;

    public PostVo(Post post, List<Reply> replies, String loginUser) {
        this.post = post;
        this.replies = ReplyHelper.convertModelToVo(replies, loginUser);
        this.canEdit = post.getUserId().equals(loginUser);
        this.replyCnt = replies.size();
    }
}
