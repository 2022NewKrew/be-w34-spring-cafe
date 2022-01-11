package com.kakao.cafe.domain.post;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    private Long id;
    private String writer;  // 로그인 기능 없이 작성자 정보를 저장하기 위해 form 에서 전달된 데이터를 매핑한다.
    private String title;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public Post(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    // 데이터베이스를 사용하지 않고, 메모리에 데이터를 저장하는 방식이기 때문에 필요함.
    // 대신에 default 접근제어자를 통해 다른 패키지의 클래스는 사용할 수 없도록 함.
    // 데이터베이스와 연결하면 삭제 필요
    void setId(Long id) {
        this.id = id;
    }
}
