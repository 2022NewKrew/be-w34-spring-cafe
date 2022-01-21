package com.kakao.cafe.impl.service;

import com.kakao.cafe.dto.*;
import com.kakao.cafe.exception.NoChangeException;
import com.kakao.cafe.exception.NoModifyPermissionException;
import com.kakao.cafe.exception.OtherUserReplyExistException;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ReplyRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Slf4j
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
    public void insertArticle(ArticleDTO article) {
        if (articleRepository.insertArticle(article) < 1)
            throw new NoChangeException(Constants.DEFAULT_ERROR_MESSAGE);
        log.info("create Article -> Writer : {}, Title : {}", article.getWriterId(), article.getTitle());
    }

    @Override
    public RestResponseDTO insertReplyAndGetReplies(ReplyDTO reply, Long userId, long lastReplyId) {
        reply.setWriterID(userId);
        if (replyRepository.insertReply(reply) < 1) {
            throw new NoChangeException(Constants.DEFAULT_ERROR_MESSAGE);
        }

        log.info("create Reply -> Article : {}, Writer : {}", reply.getArticleID(), userId);
        return getArticleReplies(reply.getArticleID(), lastReplyId);
    }

    private void validArticleOwnerShip(ArticleDTO article, UserDTO user) {
        if (!Objects.equals(article.getWriterId(), user.getId())) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
    }

    public void getArticleForm(long articleId, UserDTO user, Model model) {
        ArticleDTO article = getArticleById(articleId);
        validArticleOwnerShip(article, user);
        model.addAttribute("article", article);
        log.info("get Article(Form) -> UserID : {}, ArticleId : {}", user.getId(), article.getId());
    }

    @Override
    public void getArticleList(Long page, Model model) {
        long articleCount = articleRepository.getArticleCount();
        long maxPage = (articleCount - 1) / 15 + 1;
        if (page == null) {
            page = 1L;
        }
        if (page < 1 || page > maxPage) {
            page = maxPage; // 마지막 인덱스를 구한다.
        }
        long firstIndex = (page < 3) ? 1 : page - 2;
        long lastIndex = Math.min(firstIndex + 4, maxPage);
        List<Long> indexes = LongStream.range(firstIndex, lastIndex + 1).boxed().collect(Collectors.toList());
        model.addAttribute("articles", articleRepository.getAllArticle(page));
        model.addAttribute("needToFirst", firstIndex > 1);
        model.addAttribute("indexes", indexes);
        model.addAttribute("needToLast", lastIndex != maxPage);
        model.addAttribute("articleCount", articleCount);
    }

    @Override
    public ArticleDTO getArticleById(long articleId) {
        return articleRepository.getArticleById(articleId);
    }

    @Override
    public void getArticle(long articleId, UserDTO user, Model model) {
        articleRepository.increaseViews(articleId);
        ArticleDTO article = articleRepository.getArticleById(articleId);
        model.addAttribute("isOwner", Objects.equals(article.getWriterId(), user.getId()));
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        log.info("get Article -> articleId : {}", articleId);
    }

    @Override
    public void updateArticle(UserDTO user, long id, ArticleDTO article) {

        ArticleDTO originalArticle = articleRepository.getArticleById(id);
        validArticleOwnerShip(originalArticle, user);
        if (articleRepository.updateArticle(article.getId(), article) < 1) {
            throw new NoChangeException(NO_UPDATE_MESSAGE);
        }
        log.info("update Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
    }

    @Override
    public void deleteArticle(long id, UserDTO user) {
        ArticleDTO article = getArticleById(id);

        if (!Objects.equals(article.getWriterId(), user.getId())) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
        if (replyRepository.getOtherUserRepliesCount(id, user.getId()) > 0) {
            throw new OtherUserReplyExistException(CANNOT_DELETE_ARTICLE_MESSAGE);
        }
        if (replyRepository.deleteAllReplies(id) < 0 || articleRepository.deleteArticle(id) <= 0) {
            throw new NoChangeException(NO_DELETE_MESSAGE);
        }
        log.info("delete Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
    }


    @Override
    public RestResponseDTO deleteReply(long userId, long articleId, long replyId) {
        ReplyDTO reply = replyRepository.getReplyById(replyId);
        if (reply.getWriterID() != userId) {
            throw new NoModifyPermissionException(ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
        }
        if (replyRepository.deleteReply(replyId) <= 0) {
            throw new NoChangeException(NO_DELETE_MESSAGE);
        }

        log.info("delete Reply -> ID : {}, Article : {}, Writer : {}", replyId, articleId, userId);
        return new RestResponseDTO(true);
    }

    @Override
    public ReplyRestResponseDTO getArticleReplies(long articleId, long lastReplyId) {
        List<ReplyDTO> replyList = replyRepository.getArticleReplies(articleId, lastReplyId);
        return new ReplyRestResponseDTO(replyList);
    }
}
