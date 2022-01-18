package com.kakao.cafe.article.web;

import com.kakao.cafe.article.service.ArticleService;
import com.kakao.cafe.article.web.dto.ArticleModifyDto;
import com.kakao.cafe.article.web.dto.ArticleSaveDto;
import com.kakao.cafe.article.web.dto.ArticleShowDto;
import com.kakao.cafe.user.domain.User;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/")
    public String articleList(Model model) {
        List<ArticleShowDto> articleShowDtoList = articleService.findAllArticle();
        model.addAttribute("articles", articleShowDtoList);
        return "qna/list";
    }

    @GetMapping("/articles/{index}")
    public String articleDetail(@PathVariable("index") Long index, Model model) {
        ArticleShowDto articleShowDto = articleService.findArticle(index);
        model.addAttribute("article", articleShowDto);
        return "qna/show";
    }

    @PostMapping("/questions")
    public String articleAdd(@Valid ArticleSaveDto articleSaveDto, HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("sessionedUser");
        articleSaveDto.setWriter(user.getUserId());
        articleService.addArticle(articleSaveDto);
        return "redirect:/";
    }

    @GetMapping("/questions/{index}")
    public String questionDetail(@PathVariable("index") Long index, Model model,
        HttpSession httpSession) {
        ArticleShowDto articleShowDto = articleService.findArticle(index);
        // 자신의 글이 맞는지 validate
        User user = (User) httpSession.getAttribute("sessionedUser");
        if (!user.getUserId().equals(articleShowDto.getWriter())) {
            return "redirect:/";
        }
        model.addAttribute("article", articleShowDto);
        return "qna/editform";
    }

    @PutMapping("/questions/{index}")
    public String questionModify(@Valid ArticleModifyDto articleModifyDto,
        @PathVariable("index") Long index) {
        articleService.modifyArticle(index, articleModifyDto);
        return "redirect:/articles/" + index;
    }
}
