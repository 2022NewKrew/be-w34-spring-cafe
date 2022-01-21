package com.kakao.cafe.Dto.Post;

import com.kakao.cafe.Dto.Reply.ReplyResponseDto;
import lombok.Getter;

import java.util.List;

@Getter
public class DetailedPostResponseDto {
    private final int id;
    private final String writer;
    private final String title;
    private final String content;
    private final List<ReplyResponseDto> replyList;

    public DetailedPostResponseDto(PostResponseDto post, List<ReplyResponseDto> replyList) {
        this.id = post.getId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.replyList = replyList;
    }
}
