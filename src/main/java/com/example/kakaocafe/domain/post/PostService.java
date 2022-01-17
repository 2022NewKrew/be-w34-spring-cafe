package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.core.exception.PostBusinessException;
import com.example.kakaocafe.core.exception.HasNotPermissionException;
import com.example.kakaocafe.domain.post.comment.CommentDAO;
import com.example.kakaocafe.domain.post.dto.Post;
import com.example.kakaocafe.domain.post.dto.PostInfo;
import com.example.kakaocafe.domain.post.dto.UpdatePostForm;
import com.example.kakaocafe.domain.post.dto.WritePostForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostDAO postDAO;
    private final CommentDAO commentDAO;

    @Transactional
    public long create(WritePostForm writePostForm) {
        return postDAO.create(writePostForm);
    }

    @Transactional
    public Post findPostByIdAndPlusViewCount(long id) {
        postDAO.plusViewCount(id);

        return postDAO.getPostById(id)
                .orElseThrow();
    }

    @Transactional(readOnly = true)
    public PostInfo findPostInfoById(long postId, long writerId) {
        ifIsNotWriterThrowException(postId, writerId);

        return postDAO.getPostInfo(postId)
                .orElseThrow();
    }

    @Transactional
    public void update(UpdatePostForm updatePostForm) {
        final long postId = updatePostForm.getPostId();
        final long writerId = updatePostForm.getWriterId();

        ifIsNotWriterThrowException(postId, writerId);

        postDAO.update(updatePostForm);
    }

    @Transactional
    public void delete(long postId, long writerId) {
        ifIsNotWriterThrowException(postId, writerId);

        if (commentDAO.canDelete(postId, writerId)) {
            throw new PostBusinessException("[삭제 오류] 다른 유저의 댓글이 존재합니다");
        }

        commentDAO.deleteAllByPostId(postId);
        postDAO.delete(postId);
    }

    private void ifIsNotWriterThrowException(long postId, long writerId) {
        if (postDAO.isNotWriter(postId, writerId)) {
            throw new HasNotPermissionException();
        }
    }
}
