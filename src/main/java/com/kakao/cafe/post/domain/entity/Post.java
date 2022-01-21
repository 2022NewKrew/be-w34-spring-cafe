package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EqualsAndHashCode(of = "id")
@Getter
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
    @Size(min=3, max = 10)
    private String writerName;

    private final LocalDateTime timeWritten;

    private final Boolean isHidden;

    private final List<Comment> comments;

    public Post(String title, String content, String writerName) {
        this.id = IdGenerator.createId();
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.timeWritten = LocalDateTime.now();
        isHidden = false;
        this.comments = new ArrayList<>();
        validate();
    }

    public Post(@NonNull Long id, @NonNull String title, @NonNull String content,
                @NonNull String writerName, LocalDateTime timeWritten, boolean isHidden, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writerName = writerName;
        this.timeWritten = timeWritten;
        this.isHidden = isHidden;
        this.comments = new ArrayList<>(comments);
        validate();
    }

    protected void validate(){
        ValidationService.validate(this);
    }

    public void addComment(Comment comment){
        comments.add(Objects.requireNonNull(comment));
    }
}
