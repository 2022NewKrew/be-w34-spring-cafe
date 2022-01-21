package com.kakao.cafe.dto;

import com.kakao.cafe.model.Post;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostUpdateRequest {

    @NotBlank(message = "제목은 빈 칸일 수 없습니다.")
    @Size(min = 1, max = 100, message = "제목은 1-100자 이어야 합니다.")
    private final String title;

    @NotNull(message = "내용을 입력해야 합니다.")
    private final String content;

    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public Post toEntity(UUID writerId, UUID id) {
        return new Post.Builder(writerId, title, content)
                .id(id)
                .build();
    }
}
