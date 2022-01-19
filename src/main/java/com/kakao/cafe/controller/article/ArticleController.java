package com.kakao.cafe.controller.article;

import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
import javax.naming.NoPermissionException;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ArticleController {

    private static final int MAX_ARTICLES = 1;
    private static final int INDEX_OF_FIRST_ARTICLE = 1;

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("articles",
                articleService.getPartOfArticles(INDEX_OF_FIRST_ARTICLE, MAX_ARTICLES));
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        model.addAttribute("articles", articleService.getPartOfArticles(page, MAX_ARTICLES));
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES));
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCreateDto articleCreateDto) {
        Logger logger = LoggerFactory.getLogger(ArticleController.class);
        articleService.createArticle(articleCreateDto);
        return "redirect:/";
    }

    @GetMapping("/articles")
    public String getArticleDetail(int id, Model model) {
        ArticleDto articleDto = articleService.findArticleById(id);
        model.addAttribute("article", articleDto);
        return "qna/show";
    }

    @GetMapping("/articles/form")
    public String showArticleForm(HttpSession session) {
        if (session.isNew()) {
            return "redirect:/login/form";
        }
        return "qna/form";
    }

    @GetMapping("/articles/update")
    public String showArticleUpdateForm(int id, HttpSession session, Model model)
            throws NoPermissionException {
        ArticleDto articleDto = articleService.findArticleById(id);

        checkPermission(articleDto, session);

        model.addAttribute("article", articleDto);
        return "qna/updateForm";
    }

    @PutMapping("/articles/update")
    public String articleUpdate(int id, ArticleUpdateDto articleUpdateDto, HttpSession session) {
        articleService.updateArticle(id, articleUpdateDto);
        return "redirect:/articles?id=" + id;
    }

    @DeleteMapping("/articles/delete")
    public String deleteArticle(int id, HttpSession session) throws NoPermissionException {
        ArticleDto articleDto = articleService.findArticleById(id);

        checkPermission(articleDto, session);

        articleService.deleteArticle(id);

        return "redirect:/";
    }

    private void checkPermission(ArticleDto articleDto, HttpSession session)
            throws NoPermissionException {
        if (session.isNew()) {
            throw new NoPermissionException("로그인한 사용자만 게시글을 수정할 수 있습니다.");
        }
        String loginUserId = (String) session.getAttribute("loginUserId");
        if (!loginUserId.equals(articleDto.getWriter())) {
            throw new NoPermissionException("작성자만 게시글을 수정할 수 있습니다.");
        }
    }
}
