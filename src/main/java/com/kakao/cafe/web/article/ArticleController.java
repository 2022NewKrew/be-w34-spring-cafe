package com.kakao.cafe.web.article;

import com.kakao.cafe.web.article.domain.Article;
import com.kakao.cafe.web.article.domain.Articles;
import com.kakao.cafe.web.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    private final ArticleDao articleDao;

    Logger logger = LoggerFactory.getLogger(ArticleController.class);

    private Articles articles = new Articles();

    public ArticleController(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    // 게시판 글쓰기 양식 요청
    @GetMapping("/article/form")
    public String getArticleForm () {
        logger.info("getArticleForm");
        return "/article/form";
    }

    // 글쓰기 등록 요청
//    @PostMapping("/questions")
//    public String createArticle (Article article) {
//        logger.info("getQuestion");
//        articles.addQuestion(article);
//        return "redirect:/index";
//    }
    @PostMapping("/questions")
    public String createArticle (Article article) {
        logger.info("createArticle");
        articleDao.createArticle(article);
        return "redirect:/index";
    }


    @GetMapping("/index")
//    public String getIndex (Model model) {
//        logger.info("getIndex");
//        model.addAttribute("articles", this.articles.getArticleList());
//        return "/index";
//    }
    public String getIndex (Model model) {
        logger.info("getIndex");
        model.addAttribute("articles", articleDao.readArticles());
        return "/index";
    }

    // 특정 글 상세보기 요청
    @GetMapping("/articles/{index}")
//    public String getArticle (@PathVariable String index, Model model) {
//        logger.info("getArticle");
//        model.addAttribute(this.articles.findArticleByID(index));
//        return "/article/show";
//    }
    public String getArticle (@PathVariable String index, Model model) {
        logger.info("getArticle");
        model.addAttribute(articleDao.findById(Integer.valueOf(index)));
        return "/article/show";
    }


}
