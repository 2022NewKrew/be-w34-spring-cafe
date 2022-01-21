package com.kakao.cafe.application.article.service;

import com.kakao.cafe.application.article.dto.ArticleDetail;
import com.kakao.cafe.application.article.port.in.DeleteArticleUseCase;
import com.kakao.cafe.application.article.port.out.DeleteArticlePort;
import com.kakao.cafe.application.article.port.out.GetArticleInfoPort;
import com.kakao.cafe.application.user.dto.UserInfo;
import com.kakao.cafe.domain.article.exceptions.ArticleNotExistException;
import com.kakao.cafe.domain.user.exceptions.UnauthenticatedUserException;

public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;
    private final GetArticleInfoPort getArticleInfoPort;

    public DeleteArticleService(DeleteArticlePort deleteArticlePort, GetArticleInfoPort getArticleInfoPort) {
        this.deleteArticlePort = deleteArticlePort;
        this.getArticleInfoPort = getArticleInfoPort;
    }

    @Override
    public void delete(int id, String userIdDeletingReply, UserInfo sessionedUser)
        throws UnauthenticatedUserException, ArticleNotExistException {

        ArticleDetail articleDetail = getArticleInfoPort.findArticleDetailById(id);

        if (!articleDetail.isPossibleDeleteArticle(sessionedUser)) {
            throw new UnauthenticatedUserException("댓글을 삭제 할 권한이 없습니다.");
        }

        if (!sessionedUser.getUserId().equals(userIdDeletingReply)) {
            throw new UnauthenticatedUserException("게시글을 삭제 할 권한이 없습니다.");
        }

        deleteArticlePort.delete(id);
    }
}
