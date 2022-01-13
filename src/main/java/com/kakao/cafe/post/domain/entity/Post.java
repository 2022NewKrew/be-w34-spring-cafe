package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Post {
    private final Long id;

    @Size(min=1, max = 40)
    private String title;

    @Size(min=1)
    private String content;

    @Size(min=3, max = 5)
    private String writerName;

    private final LocalDateTime timeWritten;

    private final List<Comment> comments;

    public Post(String title, String content, String writerName) {
        this.id = IdGenerator.createId();
        this.title = Objects.requireNonNull(title);
        this.content = Objects.requireNonNull(content);
        this.writerName = Objects.requireNonNull(writerName);
        this.timeWritten = LocalDateTime.now();
        this.comments = new ArrayList<>();

        ValidationService.validate(this);
    }

    public void addComment(Comment comment){
        comments.add(Objects.requireNonNull(comment));
    }
}
