package com.kakao.cafe.controller.article;

import static com.kakao.cafe.controller.Constant.MAX_ARTICLES;
import static com.kakao.cafe.controller.Constant.PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER;
import static com.kakao.cafe.controller.Constant.PERMISSION_EXCEPTION_MESSAGE_UPDATE_ONLY_WRITER;
import static com.kakao.cafe.controller.Constant.SESSION_USER_ID;

import com.kakao.cafe.controller.Constant;
import com.kakao.cafe.service.article.ArticleService;
import com.kakao.cafe.service.article.dto.ArticleCreateDto;
import com.kakao.cafe.service.article.dto.ArticleDto;
import com.kakao.cafe.service.article.dto.ArticleUpdateDto;
import com.kakao.cafe.service.reply.dto.ReplyDto;
import java.util.List;
import javax.naming.NoPermissionException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/questions/form")
    public String postForm() {
        return "qna/form";
    }

    @GetMapping("/index/{page}")
    public String getIndexByPage(@PathVariable int page, Model model) {
        model.addAttribute("articles", articleService.getPartOfArticles(page, MAX_ARTICLES));
        model.addAttribute("pages", articleService.getPages(MAX_ARTICLES, Constant.PAGE_LIMIT, page));
        if (page - 1 >= 1) {
            model.addAttribute("previousPage", page - 1);
        }
        if (articleService.getLastPageNumber(MAX_ARTICLES) >= page + 1) {
            model.addAttribute("nextPage", page + 1);
        }
        return "index";
    }

    @PostMapping("/articles")
    public String postArticle(ArticleCreateDto articleCreateDto) {
        articleService.createArticle(articleCreateDto);
        return "redirect:/";
    }

    @GetMapping("/articles")
    public String getArticleDetail(int id, Model model) {
        ArticleDto articleDto = articleService.findArticleById(id);
        List<ReplyDto> replyDtos = articleService.getArticleReplies(id);
        model.addAttribute("article", articleDto);
        model.addAttribute("numberOfReplies", replyDtos.size());
        model.addAttribute("replies", replyDtos);
        return "qna/show";
    }

    @GetMapping("/articles/form")
    public String showArticleForm(HttpSession session) throws NoPermissionException {
        checkLogin(session, PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER);
        return "qna/form";
    }

    @GetMapping("/articles/update")
    public String showArticleUpdateForm(int id, HttpSession session, Model model)
            throws NoPermissionException {
        ArticleDto articleDto = articleService.findArticleById(id);

        checkLogin(session, PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER);
        checkUserId(session, articleDto.getUserId(),
                PERMISSION_EXCEPTION_MESSAGE_UPDATE_ONLY_WRITER);

        model.addAttribute("article", articleDto);
        return "qna/updateForm";
    }

    @PutMapping("/articles/update")
    public String updateArticle(int id, ArticleUpdateDto articleUpdateDto, HttpSession session) {
        articleService.updateArticle(id, articleUpdateDto);
        return "redirect:/articles?id=" + id;
    }

    @DeleteMapping("/articles/delete")
    public String deleteArticle(int id, HttpSession session) throws NoPermissionException {
        checkLogin(session, PERMISSION_EXCEPTION_MESSAGE_ONLY_LOGIN_USER);

        String userId = (String) session.getAttribute(SESSION_USER_ID);
        articleService.deleteArticle(id, userId);
        return "redirect:/";
    }

    private void checkLogin(HttpSession session, String errorMessage) throws NoPermissionException {
        String loginUserId = (String) session.getAttribute(SESSION_USER_ID);
        if (loginUserId == null) {
            throw new NoPermissionException(errorMessage);
        }
    }

    private void checkUserId(HttpSession session, String userId, String errorMessage)
            throws NoPermissionException {
        String loginUserId = (String) session.getAttribute(SESSION_USER_ID);
        if (!userId.equals(loginUserId)) {
            throw new NoPermissionException(errorMessage);
        }
    }
}
