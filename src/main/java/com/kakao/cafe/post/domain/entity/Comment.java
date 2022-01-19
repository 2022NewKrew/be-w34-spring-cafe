package com.kakao.cafe.post.domain.entity;

import com.kakao.cafe.util.IdGenerator;
import com.kakao.cafe.util.ValidationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class Comment {
    @NonNull
    private final Long id;

    @NonNull
    @Size(min = 3, max = 10)
    private final String writerName;

    @NonNull
    @Size(min = 1)
    private final String content;

    public Comment(String writerName, String content) {
        this.id = IdGenerator.createId();
        this.writerName = writerName;
        this.content = content;
        validate();
    }

    protected void validate(){
        ValidationService.validate(this);
    }

    @Override
    public String toString() {
        return String.format("작성자 %s \n%s", writerName, content);
    }
}
