package com.kakao.cafe.service;

import com.kakao.cafe.domain.comment.Comments;
import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.Posts;
import com.kakao.cafe.repository.CommentRepository;
import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.util.exception.throwable.UnauthorizedActionException;
import com.kakao.cafe.util.exception.throwable.UnavailableActionException;
import com.kakao.cafe.util.exception.wrappable.PostNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
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
            throw new UnauthorizedActionException("해당 post를 수정할 권한이 없습니다!");
    }

    public Posts findAll() {
        return postRepository.findAll();
    }

    public void update(Post post, String curUser) {
        if (!post.isWriter(curUser))
            throw new UnauthorizedActionException("다른 사람의 글은 수정할 수 없습니다!");
        postRepository.update(post);
    }

    public void delete(String curId, long postId) {
        Post post = findById(postId);
        if (!post.isWriter(curId))
            throw new UnauthorizedActionException("다른 사람의 글은 삭제할 수 없습니다!");

        Comments comments = commentRepository.findAll(postId);
        Comments leftComments = comments.filterExceptUserId(curId);
        if (leftComments.size() != 0)
            throw new UnavailableActionException("다른 사람의 comment가 존재합니다!");

        commentRepository.deleteAll(post.getId());
        postRepository.delete(post.getId());

    }

}
