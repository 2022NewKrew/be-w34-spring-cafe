package com.kakao.cafe.dto;

import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostCreateRequest {

    @NotBlank(message = "작성자는 빈 칸일 수 없습니다.")
    @Size(min = 1, max = 10, message = "작성자는 1-10자 이어야 합니다.")
    private final String writer;

    @NotBlank(message = "제목은 빈 칸일 수 없습니다.")
    @Size(min = 1, max = 100, message = "제목은 1-100자 이어야 합니다.")
    private final String title;

    @NotNull(message = "내용을 입력해야 합니다.")
    private final String content;

    public PostCreateRequest(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Post toEntity(User writer) {
        return new Post.Builder(writer, title, content).build();
    }
}
