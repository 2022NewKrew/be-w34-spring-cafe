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
        checkIfIsNotWriterThrowException(postId, writerId);

        return postDAO.getPostInfo(postId)
                .orElseThrow();
    }

    @Transactional
    public void update(UpdatePostForm updatePostForm) {
        final long postId = updatePostForm.getPostId();
        final long writerId = updatePostForm.getWriterId();

        checkIfIsNotWriterThrowException(postId, writerId);

        postDAO.update(updatePostForm);
    }

    @Transactional
    public void delete(long postId, long writerId) {
        checkIfCanNotDeleteThrowException(postId, writerId);

        commentDAO.deleteAllByPostId(postId);
        postDAO.delete(postId);
    }

    private void checkIfCanNotDeleteThrowException(long postId, long writerId) {
        final int numOfComments = postDAO.getNumOfComments(postId, writerId);
        // 0이면 내가 작성한 게시물이 아님
        // 1이면 삭제가능
        // 2이상이면 다른 사람이 작성한 코멘트들이 존재
        final boolean isNotMyPost = numOfComments == 0;
        final boolean isExistCommentsOfOtherPeople = numOfComments >= 2;

        if (isNotMyPost) {
            throw new HasNotPermissionException();
        } else if (isExistCommentsOfOtherPeople) {
            throw new PostBusinessException("[삭제 오류] 다른 유저의 댓글이 존재합니다");
        }
    }

    private void checkIfIsNotWriterThrowException(long postId, long writerId) {
        if (postDAO.isNotWriter(postId, writerId)) {
            throw new HasNotPermissionException();
        }
    }
}
