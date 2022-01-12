package com.kakao.cafe.Repository;

import com.kakao.cafe.model.Article.Post;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final List<Post> postList = new CopyOnWriteArrayList<>();
    private final AtomicLong sequenceId = new AtomicLong();

    public void add(Post post) {
        post.setId(sequenceId.incrementAndGet());
        postList.add(post);
    }

}
