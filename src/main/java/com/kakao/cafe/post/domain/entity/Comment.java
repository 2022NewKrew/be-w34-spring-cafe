package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.ValidationService;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.Objects;

@Getter
public class Comment {
    @Size(min = 3, max = 5)
    private final String writerName;

    @Size(min = 1)
    private final String content;

    public Comment(String writerName, String content) {
        this.writerName = Objects.requireNonNull(writerName);
        this.content = Objects.requireNonNull(content);

        ValidationService.validate(this);
    }

    @Override
    public String toString() {
        return String.format("작성자 %s \n%s", writerName, content);
    }
}
