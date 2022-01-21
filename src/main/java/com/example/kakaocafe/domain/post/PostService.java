package com.example.kakaocafe.domain.post;

import com.example.kakaocafe.core.exception.PostBusinessException;
import com.example.kakaocafe.core.exception.HasNotPermissionException;
import com.example.kakaocafe.core.meta.PageConfig;
import com.example.kakaocafe.domain.post.comment.CommentDAO;
import com.example.kakaocafe.domain.post.dto.*;
import com.example.kakaocafe.domain.post.page.PageBtn;
import com.example.kakaocafe.domain.post.page.PageBtnBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<PostOfTableRow> findAll(int page) {
        final int offset = (page - 1) * PageConfig.NUM_OF_POSTS_PER_PAGE;

        return postDAO.getAllPostOfTableRow(offset, PageConfig.NUM_OF_POSTS_PER_PAGE);
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

    @Transactional(readOnly = true)
    public List<PageBtn> makePageBtn(int page) {
        return PageBtnBuilder.builder
                .currentPage(page)
                .getNumOfPostsFunction((offset, limit) -> postDAO.numOfPosts(offset, limit).orElse(0))
                .build();
    }

    private void checkIfCanNotDeleteThrowException(long postId, long writerId) {

        final int canDelete = postDAO.canDelete(postId, writerId);
        // 0이면 내가 작성한 게시물이 아님
        // 1이면 다른 사람이 작성한 코멘트들이 존재
        // 2이면 삭제가능
        final boolean isNotMyPost = canDelete == 0;
        final boolean isExistCommentsOfOtherPeople = canDelete == 1;

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
