package com.kakao.cafe.controller;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.controller.viewdto.response.ArticleListResponse;
import com.kakao.cafe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
@Slf4j
public class RootController {

    private final ArticleService articleService;
    private final UserService userService; // 임시 게시물 생성용


    @GetMapping("/")
    public String getLandingPage(Model model) {
        log.info("GET / access, getLandingPage");
        articleService.getAllArticleViewDTO(0L);
        // 테스트용 자동 입력
        //Long id1 = userService.createUser("chunsikV", "chunsik@gmail.com", "춘식이", "12345");
        //Long id2 = userService.createUser("muzi", "email2@gmail.com", "무지", "abcd");
        //Long id3 = userService.createUser("ryans", "email3@gmail.com", "라이언", "1q2w3e4r");
        //articleService.createArticle(id1, "게시물 제목입니다.", "이것은 게시물 입니다.");
        //articleService.createArticle(id2, "새로운 게시물입니다.", "이것도 게시물 입니다.");
        model.addAllAttributes(new ArticleListResponse(articleService.getAllArticleViewDTO(0L)));
        return "index";
    }

    @GetMapping("*")
    public String wildCard(Model model) {
        log.info("RootController.wildCard redirect");
        return "redirect:";
    }
}
