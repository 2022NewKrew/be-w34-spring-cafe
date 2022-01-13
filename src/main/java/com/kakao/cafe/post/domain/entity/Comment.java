package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.util.Objects;

@AllArgsConstructor
@Getter
public class Comment {
    private final Long id;

    @Size(min = 3, max = 5)
    private final String writerName;

    @Size(min = 1)
    private final String content;

    public Comment(String writerName, String content) {
        this.id = IdGenerator.createId();
        this.writerName = Objects.requireNonNull(writerName);
        this.content = Objects.requireNonNull(content);

        ValidationService.validate(this);
    }

    @Override
    public String toString() {
        return String.format("작성자 %s \n%s", writerName, content);
    }
}
