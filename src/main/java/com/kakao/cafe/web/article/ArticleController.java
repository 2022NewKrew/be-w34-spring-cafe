package com.kakao.cafe.web.article;

import com.kakao.cafe.web.article.domain.Article;
import com.kakao.cafe.web.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class ArticleController {
    private final ArticleDao articleDao;

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @GetMapping("/")
    public String getMain() {
        logger.info("getMain");
        return "redirect:/index";
    }

    @GetMapping("/index")
    public String getIndex (Model model) {
        logger.info("getIndex");
        model.addAttribute("articles", articleDao.readArticles());
        return "index";
    }

    // 게시판 글쓰기 양식 요청
    @GetMapping("/article/form")
    public String getArticleForm () {
        logger.info("getArticleForm");
        return "article/form";
    }

    // 글쓰기 등록 요청
    @PostMapping("/questions")
    public String createArticle (Article article, HttpSession session) {
        logger.info("createArticle");
        User user = (User) session.getAttribute("sessionedUser");
        article.setWriter(user.getUserId());
        articleDao.createArticle(article);
        return "redirect:/index";
    }

    // 특정 글 상세보기 요청
    @GetMapping("/articles/{id}")
    public String getArticle (@PathVariable String id, Model model) {
        logger.info("getArticle");
        model.addAttribute(articleDao.findById(Integer.parseInt(id)));
        return "article/show";
    }

    // 글 수정하기 페이지 요청
    @GetMapping("/article/{id}/modify")
    public String getModifyForm (@PathVariable String id, Model model, HttpSession session) {
        logger.info("getModifyForm");

        User user = (User) session.getAttribute("sessionedUser");
        String writer = articleDao.findById(Integer.parseInt(id)).getWriter();

        // 글쓴이 검증
        if (user.getUserId().equals(writer)) {
            model.addAttribute(articleDao.findById(Integer.parseInt(id)));
            return "article/modify_form";
        }

        return "article/modify_failed";
    }

    // 글 수정 요청
    @PutMapping("/article/{id}/update")
    public String updateArticle (Article article, @PathVariable String id) {
        logger.info("update article");
        articleDao.updateArticle(article, Integer.parseInt(id));
        return "redirect:/articles/{id}";
    }

    // 글 삭제 요청
    @DeleteMapping("/article/{id}/delete")
    public String deleteArticle (@PathVariable String id, HttpSession session) {
        logger.info("delete article");
        User user = (User) session.getAttribute("sessionedUser");
        String writer = articleDao.findById(Integer.parseInt(id)).getWriter();

        if (user.getUserId().equals(writer)) {
            articleDao.deleteArticle(Integer.parseInt(id));
            return "redirect:/";
        }
        return "article/delete_failed";
    }
}
