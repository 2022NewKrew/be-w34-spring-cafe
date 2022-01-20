package com.kakao.cafe.article.presentation;

import com.kakao.cafe.article.application.ArticleService;
import com.kakao.cafe.article.application.dto.ArticleListResponse;
import com.kakao.cafe.article.application.dto.ArticleSaveRequest;
import com.kakao.cafe.article.application.dto.ArticleShowResponse;
import com.kakao.cafe.comment.application.CommentService;
import com.kakao.cafe.user.domain.SessionedUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static com.kakao.cafe.article.presentation.ArticleController.ARTICLE_URI;

@Controller
@Slf4j
@RequestMapping(ARTICLE_URI)
public class ArticleController {

    private final ArticleService articleService;

    public static final String ARTICLE_URI = "/articles";

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public String save(ArticleSaveRequest request, HttpSession session) {
        log.info(this.getClass() + ": 게시글 작성");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }
        articleService.save(request);
        return "redirect:/articles";
    }

    @GetMapping
    public ModelAndView findAll(Map<String, Object> model) {
        log.info(this.getClass() + ": 게시글 목록");
        List<ArticleListResponse> articleListResponses = articleService.findAll();
        model.put("articles", articleListResponses);
        return new ModelAndView("index", model);
    }

    @GetMapping("/{articleId}")
    public ModelAndView findById(@PathVariable int articleId, Map<String, Object> model) {
        log.info(this.getClass() + ": 게시글 상세보기");
        ArticleShowResponse articleShowResponse = articleService.findById(articleId);
        model.put("article", articleShowResponse);
        return new ModelAndView("qna/show", model);
    }

    @GetMapping("/{articleId}/form")
    public ModelAndView findFormById(@PathVariable int articleId, Map<String, Object> model, HttpSession session) {
        log.info(this.getClass() + ": 게시글 수정 폼");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return new ModelAndView("redirect:/auth/login");
        }
        ArticleShowResponse articleShowResponse = articleService.findById(articleId);
        model.put("article", articleShowResponse);
        return new ModelAndView("qna/updateForm", model);
    }

    @PutMapping("/{articleId}")
    public String updateById(
            @PathVariable int articleId,
            ArticleSaveRequest request,
            HttpSession session
    ) {
        log.info(this.getClass() + ": 게시글 수정");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }

        SessionedUser user = (SessionedUser) value;
        articleService.updateById(articleId, request, user);
        return "redirect:/articles";
    }

    @DeleteMapping("/{articleId}")
    public String deleteById(@PathVariable int articleId, HttpSession session) {
        log.info(this.getClass() + ": 게시글 삭제");
        Object value = session.getAttribute("sessionedUser");
        if (value == null) {
            return "redirect:/auth/login";
        }

        SessionedUser user = (SessionedUser) value;
        articleService.deleteById(articleId, user);
        return "redirect:/articles";
    }
}
