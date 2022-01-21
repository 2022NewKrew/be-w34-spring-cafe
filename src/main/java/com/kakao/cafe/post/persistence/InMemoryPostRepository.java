package com.kakao.cafe.post.persistence;

import com.kakao.cafe.post.domain.entity.Comment;
import com.kakao.cafe.post.domain.entity.Post;
import com.kakao.cafe.post.domain.repository.PostRepository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryPostRepository implements PostRepository {
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

    @Override
    public void savePostAll(List<Post> posts) {

    }

    @Override
    public void saveComment(Long postId, Comment comment) {
        Post post = idToPost.get(postId);
        post.addComment(comment);
    }

    @Override
    public void update(Long postId, String newContent) {

    }

    @Override
    public void softDelete(Long postId) {

    }

    @Override
    public void deleteComment(Long commentId) {

    }
}
