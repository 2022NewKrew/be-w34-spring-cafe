package com.kakao.cafe.domain.post;

import com.kakao.cafe.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class InMemoryPostRepository implements PostRepository {
    private static Map<Long, Post> store = new ConcurrentHashMap<>();
    private static long sequence = 0L;

    @Override
    public Post add(Post post) {
        post.setId(++sequence);
        store.put(post.getId(), post);
        return post;
    }

    @Override
    public Optional<Post> findById(long id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Post> findAllByWriter(User user) {
        return store.values().stream()
                .filter(post -> post.getWriter().equals(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(store.values());
    }
}
