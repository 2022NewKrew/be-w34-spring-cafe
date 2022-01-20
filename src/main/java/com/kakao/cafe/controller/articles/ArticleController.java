package com.kakao.cafe.controller.articles;

import com.kakao.cafe.common.authentification.Auth;
import com.kakao.cafe.common.authentification.UserIdentification;
import com.kakao.cafe.common.exception.custom.LoginFailedException;
import com.kakao.cafe.common.exception.data.ErrorCode;
import com.kakao.cafe.common.session.SessionKeys;
import com.kakao.cafe.controller.articles.dto.request.ArticleWriteRequest;
import com.kakao.cafe.controller.articles.mapper.ArticleViewMapper;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ArticleInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
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
        List<ArticleInfo> articles = articleService.getArticleAll();
        model.addAttribute("articles", articleViewMapper.toArticleItemResponseList(articles));
        return "qna/list";
    }

    @Auth
    @GetMapping("/articles/{articleId}")
    public String details(@PathVariable Long articleId, Model model) {
        ArticleInfo articleInfo = articleService.getArticleInfo(articleId);
        model.addAttribute("article", articleViewMapper.toArticleDetailResponse(articleInfo));
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
    public String update(ArticleWriteRequest articleWriteRequest) {
        return "redirect:/";
    }

    @Auth
    @GetMapping("/articles/{articleId}/form")
    public String showUpdateForm() {
        return "qna/form";
    }
}
