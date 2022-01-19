package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.OtherUserReplyExistException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Transactional
@Service("articleService")
public class ArticleServiceImpl implements ArticleService {
    private static final String ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE = "다른사용자가 작성한 내용은 수정할 수 없습니다.";
    private static final String CANNOT_DELETE_ARTICLE_MESSAGE = "타인의 댓글이 달린 글은 삭제할 수 없습니다.";
    private static final String NO_UPDATE_MESSAGE = "수정할 내용이 없습니다.";
    private static final String NO_DELETE_MESSAGE = "삭제할 내용이 없습니다.";
    @Resource(name = "articleRepository")
    ArticleRepository articleRepository;
    @Resource(name = "replyRepository")
    ReplyRepository replyRepository;

    @Override
    public long insertArticle(ArticleDTO article) {
        return articleRepository.insertArticle(article);
    }

    @Override
    public void insertReply(ReplyDTO reply, long userId) {
        reply.setWriterID(userId);
        if (replyRepository.insertReply(reply) < 1) {
            throw new NoChangeException(Constants.DEFAULT_ERROR_MESSAGE);
        }
    }

    @Override
    public void validArticleOwnerShip(ArticleDTO article, UserDTO user) {
        if (!Objects.equals(article.getWriterId(), user.getId())) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
    }

    @Override
    public List<ArticleDTO> getArticleList() {
        return articleRepository.getAllArticle();
    }

    @Override
    public ArticleDTO getArticleById(long articleId) {
        return articleRepository.getArticleById(articleId);
    }

    @Override
    public void getArticle(long articleId, UserDTO user, Model model) {
        articleRepository.increaseViews(articleId);
        ArticleDTO article = articleRepository.getArticleById(articleId);
        List<ReplyDTO> replies = replyRepository.getArticleReplies(articleId, user.getId());
        model.addAttribute("isOwner", Objects.equals(article.getWriterId(), user.getId()));
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        model.addAttribute("replies", replies);

    }

    @Override
    public void updateArticle(UserDTO user, long id, ArticleDTO article) {

        ArticleDTO originalArticle = articleRepository.getArticleById(id);
        validArticleOwnerShip(originalArticle, user);
        if (articleRepository.updateArticle(article.getId(), article) < 1) {
            throw new NoChangeException(NO_UPDATE_MESSAGE);
        }
    }

    @Override
    public void deleteArticle(long id, ArticleDTO article, UserDTO user) {
        if (!Objects.equals(article.getWriterId(), user.getId())) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
        if (replyRepository.getOtherUserRepliesCount(id, user.getId()) > 0) {
            throw new OtherUserReplyExistException(CANNOT_DELETE_ARTICLE_MESSAGE);
        }
        if (replyRepository.deleteAllReplies(id) < 0 || articleRepository.deleteArticle(id) <= 0) {
            throw new NoChangeException(NO_DELETE_MESSAGE);
        }
    }


    @Override
    public void deleteReply(long userId, long replyId) {
        ReplyDTO reply = replyRepository.getReplyById(userId, replyId);
        if (!reply.getIsOwner()) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
        if (replyRepository.deleteReply(replyId) <= 0) {
            throw new NoChangeException(NO_DELETE_MESSAGE);
        }
    }
}
