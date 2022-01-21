package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.post.reply.Reply;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String writer;
    private String title;
    private String body;

    @Column(insertable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(insertable = false)
    private Boolean isRemoved = false;

    @OneToMany(mappedBy = "post")
    private List<Reply> replies = new ArrayList<>();

    @Builder
    public Post(String writer, String title, String body) {
        this.writer = writer;
        this.title = title;
        this.body = body;
    }

    public void addReply(Reply reply) {
        replies.add(reply);
    }
}
