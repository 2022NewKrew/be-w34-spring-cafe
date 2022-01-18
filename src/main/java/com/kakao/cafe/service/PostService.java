package com.kakao.cafe.service;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.exception.PostNotFoundException;
import com.kakao.cafe.util.exception.UnauthorizedAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void deleteAll() {
        postRepository.deleteAll();
    }

    public int insert(Post post) {
        return postRepository.insert(post);
    }

    public Post findById(long postId) throws PostNotFoundException {
        try {
            return postRepository.findById(postId);
        } catch (Exception e) {
            throw new PostNotFoundException(e, postId);
        }
    }

    public void canUpdate(String curId, Post curPost) {
        if (!curPost.isWriter(curId))
            throw new UnauthorizedAction(new RuntimeException(), "해당 post를 수정할 권한이 없습니다!");
    }

    public Posts findAll() {
        return postRepository.findAll();
    }

    public void update(Post post, String curUser) {
        if (!post.isWriter(curUser))
            throw new UnauthorizedAction(new RuntimeException(), "다른 사람의 글은 수정할 수 없습니다!");
        postRepository.update(post);
    }

    public void delete(String curId, long postId) {
        Post post = findById(postId);
        if (!post.isWriter(curId))
            throw new UnauthorizedAction(new RuntimeException(), "다른 사람의 글은 삭제할 수 없습니다!");
        postRepository.delete(post.getId());

    }

}
