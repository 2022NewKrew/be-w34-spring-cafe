package com.kakao.cafe.dto.post;

import com.kakao.cafe.domain.post.Post;
import lombok.Getter;

@Getter
public class PostResDto {
    private Long postId;
    private String writer;
    private String title;
    private String contents;

    public PostResDto(Post post){
        this.postId = post.getPostId();
        this.writer = post.getWriter();
        this.title = post.getTitle();
        this.contents = post.getContents();
    }
}
