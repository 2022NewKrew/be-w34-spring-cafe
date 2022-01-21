package com.kakao.cafe.controller.articles;

import com.kakao.cafe.common.authentification.Auth;
import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.exception.custom.UpdateFailedException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.session.SessionKeys;
import com.kakao.cafe.controller.articles.dto.request.ArticleUpdateRequest;
import com.kakao.cafe.controller.articles.dto.request.ArticleWriteRequest;
import com.kakao.cafe.controller.articles.dto.request.ReplyWriteRequest;
import com.kakao.cafe.controller.articles.dto.response.ReplyResponse;
import com.kakao.cafe.controller.articles.mapper.ArticleViewMapper;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import com.kakao.cafe.service.article.dto.ReplyInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleViewMapper articleViewMapper;

    @GetMapping("/")
    public String list(Model model) {
        List<ArticleInfo> articles = articleService.getAll();
        model.addAttribute("articles", articleViewMapper.toArticleItemResponseList(articles));
        return "qna/list";
    }

    @Auth
    @GetMapping("/articles/{articleId}")
    public String details(@PathVariable Long articleId,
                          Model model,
                          @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        ArticleInfo articleInfo = articleService.get(articleId, loginInfo.getUserId());
        model.addAttribute("article", articleViewMapper.toArticleDetailResponse(articleInfo));

        List<ReplyInfo> replies = articleService.getReplies(articleId, loginInfo.getUserId());
        model.addAttribute("replies", articleViewMapper.toReplyListResponse(replies));
        return "qna/show";
    }

    @Auth
    @PostMapping("/articles")
    public String write(ArticleWriteRequest articleWriteRequest,
                        @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        articleService.write(loginInfo.getUserId(), articleWriteRequest.getTitle(), articleWriteRequest.getContents());
        return "redirect:/";
    }

    @Auth
    @GetMapping("/articles/form")
    public String showWriteForm() {
        return "qna/form";
    }

    @Auth
    @PutMapping("/articles/{articleId}")
    public String update(@PathVariable Long articleId,
                         ArticleUpdateRequest updateRequest,
                         @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        articleService.update(loginInfo.getUserId(), articleId, updateRequest.getTitle(), updateRequest.getContents());
        return "redirect:/";
    }

    @Auth
    @GetMapping("/articles/{articleId}/form")
    public String showUpdateForm(@PathVariable Long articleId,
                                 Model model,
                                 @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        ArticleInfo articleInfo = articleService.get(articleId, loginInfo.getUserId());
        if(!loginInfo.matchesUserId(articleInfo.getWriterId())) {
            throw new UpdateFailedException(ErrorCode.ARTICLE_UPDATER_INCORRECT);
        }
        model.addAttribute("article", articleViewMapper.toArticleUpdateFormResponse(articleInfo));
        return "qna/update_form";
    }

    @Auth
    @DeleteMapping("/articles/{articleId}")
    public String delete(@PathVariable Long articleId,
                         @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        articleService.delete(articleId, loginInfo.getUserId());
        return "redirect:/";
    }

    @Auth
    @PostMapping("/articles/{articleId}/replies")
    public String writeReply(@PathVariable Long articleId,
                             String comment,
                             @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo){
        articleService.writeReply(articleId, loginInfo.getUserId(), comment);
        return "redirect:/articles/" + articleId;
    }

    @Auth
    @DeleteMapping("/articles/{articleId}/replies/{replyId}")
    public String deleteReply(@PathVariable Long articleId,
                         @PathVariable Long replyId,
                         @SessionAttribute(name = SessionKeys.USER_IDENTIFICATION) UserIdentification loginInfo) {
        articleService.deleteReply(replyId, loginInfo.getUserId());
        return "redirect:/articles/" + articleId;
    }
}
