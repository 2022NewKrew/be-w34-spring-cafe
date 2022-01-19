package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.PostConstruct;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@AllArgsConstructor
public class Post {
    @NonNull
    private final Long id;

    @NonNull
    @Size(min=1, max = 40)
    private String title;

    @NonNull
    @Size(min=1)
    private String content;

    @NonNull
    @Size(min=3, max = 5)
    private String writerName;

    private final LocalDateTime timeWritten;

    private final List<Comment> comments;

    public Post(String title, String content, String writerName) {
        this.id = IdGenerator.createId();
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.timeWritten = LocalDateTime.now();
        this.comments = new ArrayList<>();
        validate();
    }

    protected void validate(){
        ValidationService.validate(this);
    }

    public void addComment(Comment comment){
        comments.add(Objects.requireNonNull(comment));
    }
}
