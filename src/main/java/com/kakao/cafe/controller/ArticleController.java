package com.kakao.cafe.controller;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.User;
import com.kakao.cafe.dto.SampleArticleForm;
import com.kakao.cafe.dto.SampleReplyForm;
import com.kakao.cafe.dto.SampleUserForm;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.util.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.kakao.cafe.util.ErrorCode.NOT_EXIST_USER;
import static com.kakao.cafe.util.ErrorCode.WRONG_PAGE_ACCESS;

@Controller
@RequestMapping("/article")
public class ArticleController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("")
    public String articles(Model model, HttpSession session){
        logger.info("article list print");
        Object value = session.getAttribute("user");
        if (value != null){
            User user = (User) value;
            model.addAttribute("currentUser", user);
            logger.info("User session get {}", user);
        }
        model.addAttribute("articles", articleService.getArticles());
        return "article/articleListPage";
    }

    @GetMapping("/{articleID}")
    public String article(Model model, @PathVariable Long articleID, HttpSession session){
        logger.info("article print articleID : {}", articleID);

        Article article = articleService.findArticle(articleID);
        model.addAttribute("article", article);
        model.addAttribute("replies", articleService.getReplies(articleID));

        Object value = session.getAttribute("user");
        if (value != null && ((User) value).getUid().equals(article.getAuthor())){
            User user = (User) value;
            model.addAttribute("currentUser", user);
            logger.info("User session get {}", user);
        }
        return "article/articlePage";
    }

    @GetMapping("/write")
    public String writeArticle(){
        logger.info("writeArticle page");
        return "article/articleForm";
    }

    @PostMapping("/create")
    public String createArticle(SampleArticleForm form, HttpSession session){
        logger.info("userCreate print details : {}" ,form.toString());
        Object value = session.getAttribute("user");
        if (value != null) {
            String author = ((User) value).getUid();
            articleService.addArticle(author, form);
        } else{
            throw new CustomException(NOT_EXIST_USER);
        }
        return "redirect:/article";
    }

    @GetMapping("/{articleID}/update")
    public String updateArticle(Model model, @PathVariable Long articleID, HttpSession session){
        logger.info("updateArticle print userID : {}", articleID);
        Article article = articleService.findArticle(articleID);

        Object value = session.getAttribute("user");
        if (value == null || ((User) value).getUid() != article.getAuthor()){
            throw new CustomException(WRONG_PAGE_ACCESS);
        }

        model.addAttribute("article", article);
        return "article/articleUpdateForm";
    }

    @PostMapping("/reply")
    public String addReply(SampleReplyForm form, HttpSession session) {

        Object value = session.getAttribute("user");
        if (value != null){
            User user = (User) value;
            articleService.addReply(form, user);
            logger.info("User session get {}", user);
        }
        return "redirect:/article";
    }


    @PutMapping("/{articleID}/update")
    @ResponseBody
    public String updateArticle(SampleArticleForm form, @PathVariable Long articleID){
        logger.info("updateArticle put {}" ,form.toString());
        articleService.updateArticle(articleID, form);
        return "<script>alert('Update Success');location.href='/article'</script>";
    }

    @DeleteMapping("/{articleID}")
    public String deleteArticle(@PathVariable Long articleID, HttpSession session){
        logger.info("delete Articles {}", articleID);

        Object value = session.getAttribute("user");
        Article article = articleService.findArticle(articleID);
        if ((value == null) || (!((User) value).getUid().equals(article.getAuthor()))){
            logger.info("Wrong page access error {}, {}", article.getAuthor(), ((User) value).getUid());
            throw new CustomException(WRONG_PAGE_ACCESS);
        }
        articleService.deleteArticle(articleID);
        return "redirect:/article";
    }

    @ExceptionHandler(CustomException.class)
    public Object exceptionHandle(Model model, CustomException e){
        logger.error("Error exception, {}", e.getErrorCode());
        model.addAttribute("ErrorCode", e.getErrorCode().getHttpStatus());
        model.addAttribute("ErrorMessage", e.getErrorCode().getDetail());
        return "error";
    }
}
