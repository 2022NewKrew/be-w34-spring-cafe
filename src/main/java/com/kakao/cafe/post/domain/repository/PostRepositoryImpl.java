package com.kakao.cafe.post.domain.repository;

import com.kakao.cafe.post.domain.entity.Post;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class PostRepositoryImpl implements PostRepository{
    private final Map<Long, Post> idToPost = new ConcurrentHashMap<>();
    private final List<Post> postList = new CopyOnWriteArrayList<>();

    @Override
    public Optional<Post> getPost(Long id) {
        return Optional.ofNullable(idToPost.getOrDefault(id, null));
    }

    @Override
    public List<Post> getPosts(int start, int size) {
        if(postList.size() < start + size){
            return Collections.unmodifiableList(postList.subList(start, postList.size()));
        }

        return Collections.unmodifiableList(postList.subList(start, start + size));
    }

    @Override
    public void savePost(Post post) {
        idToPost.put(post.getId(), post);
        postList.add(post);
    }
}
