package com.kakao.cafe.dto.post;

import com.kakao.cafe.domain.post.Post;
import lombok.Data;

import java.time.format.DateTimeFormatter;

@Data
public class ShowPostDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private String regDateTime;

    public ShowPostDto(Post post) {
        id = post.getId();
        title = post.getTitle();
        content = post.getContent();
        writer = post.getWriter();
        regDateTime = post.getRegDateTime().format(DateTimeFormatter.ofPattern("[yyyy-MM-dd] HH:mm:ss"));
    }
}
