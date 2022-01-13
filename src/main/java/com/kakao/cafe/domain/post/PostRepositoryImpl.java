package com.kakao.cafe.domain.post;

import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.util.*;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private final Map<Long, Post> posts = new HashMap<>();

    private Long SEQ_NO_OF_POSTS = 0L;

    @Override
    public void save(Post post) {
        if (ObjectUtils.isEmpty(post)) {
            throw new IllegalArgumentException("게시물 정보가 없어서 게시글을 저장할 수 없습니다.");
        }
        post.setId(++SEQ_NO_OF_POSTS);
        posts.put(SEQ_NO_OF_POSTS, post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Optional<Post> findById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    @Override
    public void deleteAll() {
        posts.clear();
    }

}
