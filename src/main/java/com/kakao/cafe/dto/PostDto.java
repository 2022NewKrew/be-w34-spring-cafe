package com.kakao.cafe.dto;

import com.kakao.cafe.domain.post.Post;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostDto {
    private String writer;
    private String title;
    private String content;

    public Post getPost(){
        Post post = new Post();
        post.setWriter(writer);
        post.setTitle(title);
        post.setContent(content);

        return post;
    }
}
