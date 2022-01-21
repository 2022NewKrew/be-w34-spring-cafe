package com.kakao.cafe.controller;

import com.kakao.cafe.domain.article.dto.ArticleResponse;
import com.kakao.cafe.domain.article.dto.PageInfo;
import com.kakao.cafe.domain.user.User;
import com.kakao.cafe.core.SessionConst;
import com.kakao.cafe.domain.article.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String mainPage(Model model, @SessionAttribute(name = SessionConst.LOGIN_COOKIE, required = false) User user) {
        List<ArticleResponse> questionPostList = articleService.findAll();
        model.addAttribute("questions", questionPostList);
        return "index";
    }

    @GetMapping("/error")
    public String errorPage() {
        return "error";
    }

    /**
     * 시작 번호 끝번호
     * 한 페이지당 데이터 수 15.
     */
    @GetMapping("/page")
    @ResponseBody
    public PageInfo getPageInfo() {
        return articleService.getPageInfo();
    }


    @GetMapping("/page/{pageNum}")
    @ResponseBody
    public List<ArticleResponse> getPage(@PathVariable int pageNum) {
        return articleService.getListByPagenum(pageNum);
    }
}
