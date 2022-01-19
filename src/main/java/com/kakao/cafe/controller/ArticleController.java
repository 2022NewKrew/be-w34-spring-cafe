package com.kakao.cafe.controller;

import com.kakao.cafe.annotation.SessionCheck;
import com.kakao.cafe.dto.ArticleDTO;
import com.kakao.cafe.dto.ReplyDTO;
import com.kakao.cafe.dto.UserDTO;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private static final String ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE = "다른사용자가 작성한 내용은 수정할 수 없습니다.";
    private static final String CANNOT_DELETE_ARTICLE_MESSAGE = "타인의 댓글이 달린 글은 삭제할 수 없습니다.";
    @Resource(name = "articleService")
    private ArticleService articleService;
    @Resource(name = "replyService")
    private ReplyService replyService;

    @GetMapping
    String posts(Model model) {
        model.addAttribute("articles", articleService.getArticleList());
        return "article/list";
    }

    @GetMapping("/form")
    @SessionCheck
    String form(HttpSession session) {
        return "article/form";
    }

    @PostMapping
    @SessionCheck
    String articles(HttpSession session, @Valid ArticleDTO article, Model model) {
        if (articleService.insertArticle(article) < 1) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }

        log.info("create Article -> Writer : {}, Title : {}", article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    @SessionCheck
    String getArticleForm(HttpSession session, @PathVariable long id, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);
        if (!Objects.equals(article.getWriterId(), user.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        model.addAttribute("article", article);
        log.info("get Article(Form) -> UserID : {}, ArticleId : {}", user.getId(), article.getId());

        return "article/updateForm";
    }

    @PutMapping("/{id}/update")
    @SessionCheck
    String updateArticle(HttpSession session, @PathVariable long id, @Valid ArticleDTO article, Model model) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO articleInfo = articleService.getArticleById(id);
        if (!Objects.equals(articleInfo.getWriterId(), sessionUser.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (articleService.updateArticle(id, article) <= 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("update Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @DeleteMapping("/{id}/delete")
    @SessionCheck
    String deleteArticle(HttpSession session, @PathVariable long id, Model model) {
        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        ArticleDTO article = articleService.getArticleById(id);
        if (!Objects.equals(article.getWriterId(), sessionUser.getId())) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (replyService.getOtherUserRepliesCount(id, sessionUser.getId()) > 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, CANNOT_DELETE_ARTICLE_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (articleService.deleteArticle(id) <= 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (replyService.deleteAllReplies(id) < 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("delete Article -> ID : {}, Writer : {}, Title : {}", id, article.getWriterId(), article.getTitle());
        return "redirect:/";
    }

    @GetMapping("/{articleId}")
    @SessionCheck
    String show(HttpSession session, @PathVariable long articleId, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        articleService.increaseViews(articleId);
        ArticleDTO article = articleService.getArticleById(articleId);
        List<ReplyDTO> replies = replyService.getArticleReplies(articleId, user.getId());
        model.addAttribute("isOwner", Objects.equals(article.getWriterId(), user.getId()));
        model.addAttribute("article", article);
        model.addAttribute("articleId", articleId);
        model.addAttribute("replies", replies);
        log.info("get Article -> articleId : {}", articleId);
        return "article/show";
    }

    @PostMapping("/{articleId}/reply/")
    @SessionCheck
    String insertReply(HttpSession session, @PathVariable long articleId, @Valid ReplyDTO reply, Model model) {
        UserDTO user = (UserDTO) session.getAttribute("sessionUser");
        long userId = user.getId();
        reply.setWriterID(userId);
        if (replyService.insertReply(reply) < 1) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("create Reply -> Article : {}, Writer : {}", articleId, userId);
        return "redirect:/articles/" + articleId;
    }

    @DeleteMapping("/{articleId}/reply/{replyId}")
    @SessionCheck
    String deleteReply(HttpSession session, @PathVariable long articleId, @PathVariable long replyId, Model model) {

        UserDTO sessionUser = (UserDTO) session.getAttribute("sessionUser");
        long userId = sessionUser.getId();
        ReplyDTO reply = replyService.getReplyById(userId, replyId);
        if (!reply.getIsOwner()) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, ARTICLE_UPDATE_NOT_ALLOWED_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        if (replyService.deleteReply(replyId) <= 0) {
            model.addAttribute(Constants.ERROR_MESSAGE_ATTRIBUTE_NAME, Constants.DEFAULT_ERROR_MESSAGE);
            return Constants.ERROR_PAGE_NAME;
        }
        log.info("delete Reply -> ID : {}, Article : {}, Writer : {}", replyId, articleId, userId);

        return "redirect:/articles/" + articleId;
    }


}
