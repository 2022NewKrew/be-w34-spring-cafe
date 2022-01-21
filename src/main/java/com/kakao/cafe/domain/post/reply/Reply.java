package com.kakao.cafe.domain.post.reply;

import com.kakao.cafe.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String body;

    @Column(insertable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private Boolean isRemoved = false;

    @ManyToOne
//    @Setter
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    public Reply(String writer, String body) {
        this.writer = writer;
        this.body = body;
    }
}
